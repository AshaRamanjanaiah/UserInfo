package com.userinfo.au.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.au.R
import com.userinfo.au.databinding.AlbumListItemBinding
import com.userinfo.au.model.Album

class AlbumListAdapter(private val albumsList: ArrayList<Album>): RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder>() {

    fun updateUsersList(newAlbumsList: List<Album>) {
        albumsList.clear()
        albumsList.addAll(newAlbumsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<AlbumListItemBinding>(inflater,
            R.layout.album_list_item, parent, false)

        return AlbumListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        holder.view.album = albumsList[position]
    }

    class AlbumListViewHolder(var view: AlbumListItemBinding): RecyclerView.ViewHolder(view.root)
}