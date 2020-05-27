package com.userinfo.au.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.au.R

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_list_item, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {

    }

    class UserListViewHolder(var view: View): RecyclerView.ViewHolder(view)
}