package com.todaysquare.iamportexample.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

import com.todaysquare.iamportexample.utils.Constants.String.Companion.KEY_SAVED_AT

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSaveAt(saveAt: String) {
        preference.edit().putString(KEY_SAVED_AT, saveAt).apply()

    }

    fun getLastSavedAt(): String? = preference.getString(KEY_SAVED_AT, null)

}