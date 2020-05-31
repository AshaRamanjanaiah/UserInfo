package com.userinfo.au.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.userinfo.au.R
import com.userinfo.au.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * User info list fragment
 */
class UserListFragment : Fragment() {

    private val userListAdapter = UserListAdapter(arrayListOf())

    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_user_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }

        activity?.let {
            viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

            viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->
                userListAdapter.updateUsersList(userList)
            })
            viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    loadError.visibility = View.GONE
                }
            })
            viewModel.loadError.observe(viewLifecycleOwner, Observer { isError ->
                loadError.visibility = if (isError) View.VISIBLE else View.GONE
            })
        }

        refreshLayout.setOnRefreshListener {
            loadError.visibility = View.GONE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

    }


}
