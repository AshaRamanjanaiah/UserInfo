package com.userinfo.au.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.userinfo.au.R
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * A simple [Fragment] subclass.
 */
class UserListFragment : Fragment() {

    val userListAdapter = UserListAdapter()

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
    }


}