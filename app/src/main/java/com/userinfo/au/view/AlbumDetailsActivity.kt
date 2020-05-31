package com.userinfo.au.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.userinfo.au.R
import com.userinfo.au.databinding.ActivityAlbumDetailsBinding
import com.userinfo.au.model.Album
import com.userinfo.au.utils.Utils

class AlbumDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var album = intent.getParcelableExtra<Album>(Utils.ALBUM)

        val binding: ActivityAlbumDetailsBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_album_details)

        binding.album = album
    }
}
