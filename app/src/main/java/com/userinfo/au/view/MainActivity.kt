package com.userinfo.au.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.userinfo.au.R
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar_title.text = getString(R.string.app_name)
    }
}
