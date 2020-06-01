package com.userinfo.au.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.squareup.picasso.Picasso
import com.userinfo.au.R

/**
 * Utility method to create a Circular Progress Drawable
 * @param context
 */
fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

/**
 * Utility method which downloads image using Glide library and displays it in the view
 * @param Image URL
 * @param Circular progress drawable
 */
fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {
    Picasso.get()
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.loadImage(url, getProgressDrawable(view.context))
}