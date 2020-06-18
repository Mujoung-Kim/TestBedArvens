package com.todaysquare.restapiexample.data.preferences

import android.content.Context
import android.content.SharedPreferences

import com.todaysquare.restapiexample.R

class MySharedPreferences {
    private var sharedPreferences: SharedPreferences
    private var editor : SharedPreferences.Editor
    private val SHARE_NAME = "SHARE_PREF"
    private var context: Context

    constructor(context: Context) {
        this.context = context
        sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

    }

    fun getUID() : String? = sharedPreferences.getString(context.getString(R.string.uid), null)

    fun isLoggedIn() : Boolean {
        if (!sharedPreferences.getBoolean(context.getString(R.string.logged_in), false))
            return false
        return true

    }

    fun login(UID: String) {
        editor.putBoolean(context.getString(R.string.logged_in), true)
        editor.putString(context.getString(R.string.uid), UID)
        editor.apply()

    }

    fun logout() {
        editor.putBoolean(context.getString(R.string.logged_in), false)
        editor.remove(context.getString(R.string.uid))
        editor.apply()

    }
}