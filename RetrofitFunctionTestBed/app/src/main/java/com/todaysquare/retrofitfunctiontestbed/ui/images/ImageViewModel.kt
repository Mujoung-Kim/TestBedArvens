package com.todaysquare.retrofitfunctiontestbed.ui.images

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel

import java.io.FileOutputStream
import java.text.SimpleDateFormat

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        const val TAG = "ImageViewModel Test"

    }
    private val context = application.applicationContext

    @SuppressLint("LogNotTimber")
    fun saveImageFile(filename: String, mimeType: String, bitmap: Bitmap): Uri? {
        val values = ContentValues()

        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            values.put(MediaStore.Images.Media.IS_PENDING, 1)

        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        try {
            if (uri != null) {
                val descriptor =
                    context.contentResolver.openFileDescriptor(uri, "rw")

                if (descriptor != null) {
                    val fileOutputStream = FileOutputStream(descriptor.fileDescriptor)

                    //  bitmap type 분류 찾아보기
                    /*when (bitmap) {
                        Bitmap.CompressFormat.JPEG -> bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , fileOutputStream)

                    }*/
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                    fileOutputStream.close()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        values.clear()
                        values.put(MediaStore.Images.Media.IS_PENDING, 0)
                        context.contentResolver.update(uri, values, null, null)

                    }
                }
            }
        } catch (error: java.lang.Exception) {
            Log.e(TAG, "error = ${error.localizedMessage}")

        }
        return uri

    }

    @SuppressLint("SimpleDateFormat")
    fun newFileName(): String {
        val simpleDateFormat = SimpleDateFormat("yyyyMMDD_HHmmss")
        val fileName = simpleDateFormat.format(System.currentTimeMillis())

        return "$fileName.jpg"

    }
}