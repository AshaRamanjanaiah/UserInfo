package com.userinfo.au.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.userinfo.au.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = UserListFragment()
        fragmentTransaction.add(R.id.user_list, fragment)
        fragmentTransaction.commit()
    }
}
