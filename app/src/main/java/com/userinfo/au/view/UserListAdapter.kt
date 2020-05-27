package com.userinfo.au.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.au.R
import com.userinfo.au.model.User
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserListAdapter(private val usersList: ArrayList<User>): RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    fun updateUsersList(newUsersList: List<User>) {
        usersList.clear()
        usersList.addAll(newUsersList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_list_item, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.view.tv_user_id.text = usersList[0].id.toString()
        holder.view.tv_name.text = usersList[0].name
        holder.view.tv_email.text = usersList[0].email
        holder.view.tv_phone.text = usersList[0].phone
    }

    class UserListViewHolder(var view: View): RecyclerView.ViewHolder(view)
}