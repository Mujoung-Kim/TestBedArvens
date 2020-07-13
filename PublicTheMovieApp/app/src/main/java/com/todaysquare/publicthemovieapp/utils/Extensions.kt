package com.todaysquare.publicthemovieapp.utils

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.squareup.picasso.Picasso
import com.todaysquare.publicthemovieapp.R
import org.jetbrains.anko.design.snackbar

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loading(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl))
        Picasso.get().load(R.mipmap.ic_launcher).into(this)
    else
        Picasso.get().load(imageUrl)
            .placeholder(R.drawable.img_default)
            .centerCrop()
            .fit()
            .into(this)

}

/*fun ImageView.downloading(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl))
        snackbar("Not found imageUrl")
    else {
        //  input the download function.

    }
}*/

fun <T> androidLazy(initializer: () -> T) : Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)