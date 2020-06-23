package com.todaysquare.iamportexample.utils

import android.content.Context
import android.view.View
import android.widget.Toast

import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }

fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackBar ->
        snackBar.setAction("OK") { snackBar.dismiss() }.show()

    }
}