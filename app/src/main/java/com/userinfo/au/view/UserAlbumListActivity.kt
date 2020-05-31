package com.userinfo.au.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.userinfo.au.R
import com.userinfo.au.utils.Utils
import com.userinfo.au.viewmodel.AlbumViewModel
import com.userinfo.au.viewmodel.UserIdModelFactory
import kotlinx.android.synthetic.main.activity_user_album_list.*
import kotlinx.android.synthetic.main.fragment_album_list.*
import kotlinx.android.synthetic.main.fragment_album_list.album_title
import kotlinx.android.synthetic.main.fragment_album_list.loadError
import kotlinx.android.synthetic.main.fragment_album_list.loading
import kotlinx.android.synthetic.main.fragment_album_list.rv_album_list
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.toolbar.*


class UserAlbumListActivity : AppCompatActivity() {

    private val albumListAdapter = AlbumListAdapter(arrayListOf())

    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_album_list)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userId = intent.getIntExtra(Utils.USER_ID, 0)

        rv_album_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumListAdapter
        }

        val viewModelFactory = UserIdModelFactory(userId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AlbumViewModel::class.java)

        viewModel.albumList.observe(this, Observer { albumList ->
            albumListAdapter.updateUsersList(albumList)
        })

        viewModel.title.observe(this, Observer { title ->
            toolbar_title.text = getString(R.string.album_id, title.toString())
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) {
                loadError.visibility = View.GONE
            }
        })
        viewModel.loadError.observe(this, Observer { isError ->
            loadError.visibility = if (isError) View.VISIBLE else View.GONE
        })

        refreshAlbumLayout.setOnRefreshListener {
            loadError.visibility = View.GONE
            viewModel.refresh()
            refreshAlbumLayout.isRefreshing = false
        }
    }
}
