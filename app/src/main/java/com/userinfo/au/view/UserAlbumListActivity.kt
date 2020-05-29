package com.userinfo.au.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.userinfo.au.R

class UserAlbumListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_album_list)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = UserListFragment()
        fragmentTransaction.add(R.id.album_list, fragment)
        fragmentTransaction.commit()
    }
}
