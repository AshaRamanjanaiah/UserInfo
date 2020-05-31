package com.userinfo.au.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.userinfo.au.R
import com.userinfo.au.databinding.ActivityAlbumDetailsBinding
import com.userinfo.au.model.Album
import com.userinfo.au.utils.Utils
import kotlinx.android.synthetic.main.toolbar.*

class AlbumDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var album = intent.getParcelableExtra<Album>(Utils.ALBUM)

        val binding: ActivityAlbumDetailsBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_album_details)

        binding.album = album

        toolbar_title.text = getString(R.string.album_id, album.albumId.toString())
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
