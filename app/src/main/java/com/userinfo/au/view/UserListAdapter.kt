package com.userinfo.au.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.au.R
import com.userinfo.au.databinding.UserListItemBinding
import com.userinfo.au.model.User
import com.userinfo.au.utils.Utils

class UserListAdapter(private val usersList: ArrayList<User>): RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    fun updateUsersList(newUsersList: List<User>) {
        usersList.clear()
        usersList.addAll(newUsersList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<UserListItemBinding>(
            inflater,
            R.layout.user_list_item, parent, false
        )
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.view.user = usersList[position]
        holder.view.userListLayout.setOnClickListener {
            val intent = Intent(it.context, UserAlbumListActivity::class.java)
            intent.putExtra(Utils.USER_ID, usersList[position].id)
            it.context.startActivity(intent)
        }
    }

    class UserListViewHolder(var view: UserListItemBinding): RecyclerView.ViewHolder(view.root)
}