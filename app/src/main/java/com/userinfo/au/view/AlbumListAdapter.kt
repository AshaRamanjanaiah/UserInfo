package com.userinfo.au.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.au.R

class AlbumListAdapter: RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.album_list_item, parent, false)

        return AlbumListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {

    }

    class AlbumListViewHolder(view: View): RecyclerView.ViewHolder(view)
}