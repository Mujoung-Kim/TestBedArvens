package com.todaysquare.asynctaskdownload.ui.images

import android.app.Application
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

import com.todaysquare.asynctaskdownload.data.models.Images

class ImagesViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    fun getImageList(): List<Images> {
        val listUrl = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE)
        val cursor = context.contentResolver.query(listUrl, projection,
            null, null, null)
        val imageList = mutableListOf<Images>()

        while (cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val displayName = cursor.getString(1)
            val mineType = cursor.getString(2)
            val size = cursor.getLong(3)
            val image = Images(id, displayName, mineType, size)

            imageList.add(image)

        }

        return imageList

    }
}