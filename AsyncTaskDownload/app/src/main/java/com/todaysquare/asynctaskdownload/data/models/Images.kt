package com.todaysquare.asynctaskdownload.data.models

import android.net.Uri
import android.provider.MediaStore

class Images (id: String, displayName: String, mineType: String, size: Long){
    var id: String = ""
    var displayName: String
    var mineType: String
    var size: Long

    init {
        this.id = id
        this.displayName = displayName
        this.mineType = mineType
        this.size = size

    }

    fun getImageUri(): Uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

}