package com.todaysquare.retrofittest.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.todaysquare.retrofittest.App
import com.todaysquare.retrofittest.R
import com.todaysquare.retrofittest.data.network.responses.ActivityExerciseSearchModel
import com.todaysquare.retrofittest.data.network.responses.ActivityRecordInputModel
import com.todaysquare.retrofittest.data.network.responses.ProfileInfoModel
import com.todaysquare.retrofittest.data.network.responses.ProfileReplaceModel

import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.io.FileNotFoundException


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class BaseActivity : AppCompatActivity() {
    private var context: Context? = null
    private val app = App()
    private var filePath: Uri? = null

    @Throws(FileNotFoundException::class)
    fun provider() {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.IS_PENDING)
        val sortOrder = "${MediaStore.Images.Media._ID} ASC"
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection, null, null, sortOrder
        )?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            if (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val contentUri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id.toString())

                filePath = contentUri
                Log.d("The end", "$filePath")

            }
        }
        return cursor!!

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        provider()
        setContentView(R.layout.activity_base)

        context = this@BaseActivity
        app.getInstance().getApiManager()?.getApiResponse(
            ActivityExerciseSearchModel.Companion.RS::class.java,
            app.getInstance().getApiManager()!!.getApiService(context)
                ?.getExerciseSearchList("")!!,
            exerciseSearchListener)


        val bitmap: Bitmap = BitmapFactory.decodeFile("$filePath")

        val data: String? = app.getInstance().getGson()?.toJson(
            ActivityRecordInputModel.Companion.RQ(1, "running"))
        val body = RequestBody
            .create(MediaType.parse("application/json;charset=utf-8"), data)

        app.getInstance().getApiManager()?.getApiResponse(
            ActivityRecordInputModel.Companion.RS::class.java,
            app.getInstance().getApiManager()!!.getApiService(context)
                ?.inputExerciseRecord(body)!!, exerciseRecordInputListener)

        val infoModel = ProfileInfoModel("여", "Fwang", 70, 180)
        val rqProfile = ProfileReplaceModel.RQ("비트맵 url", bitmap, infoModel)

        app.getInstance().getApiManager()?.getApiResponse(
            ProfileReplaceModel.RS::class.java,
            app.getInstance().getApiManager()!!.getApiService(context)
                ?.getUpdateProfileInfo(rqProfile.mpFile, rqProfile.rqMap)!!, profileUpdateListener)

    }

    private val exerciseSearchListener: IHTTPListener = object : IHTTPListener {
        override fun onSuccess(result: Any?) {
            val response: ActivityExerciseSearchModel.Companion.RS? =
                result as ActivityExerciseSearchModel.Companion.RS?

        }

        override fun onFail(code: Int, message: String?, result: Any?) {
            Log.d("The end", "Fail code: $code, message: $message")

        }
    }

    private val exerciseRecordInputListener: IHTTPListener = object : IHTTPListener {
        override fun onSuccess(result: Any?) {
            val response: ActivityRecordInputModel.Companion.RS? =
                result as ActivityRecordInputModel.Companion.RS?

        }

        override fun onFail(code: Int, message: String?, result: Any?) {
            Log.d("The end", "Fail code: $code, message: $message")

        }
    }

    private val profileUpdateListener: IHTTPListener = object : IHTTPListener {
        override fun onSuccess(result: Any?) {
            val response = result as ProfileReplaceModel.RS?

        }

        override fun onFail(code: Int, message: String?, result: Any?) {
            Log.d("The end", "Fail code: $code, message: $message")

        }
    }
}
