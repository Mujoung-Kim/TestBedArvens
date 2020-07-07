@file:JvmName("ExtensionsUtils")

package com.todaysquare.publicmovieapisample.utils

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.todaysquare.publicmovieapisample.R

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl))
        Glide.with(this).load(R.mipmap.ic_launcher).into(this)
    else {
        Glide.with(this).load(imageUrl)
            .placeholder(R.drawable.image_default)
            .centerCrop()
            .into(this)

    }
}

fun <T> androidLazy(initializer: () -> T) : Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)