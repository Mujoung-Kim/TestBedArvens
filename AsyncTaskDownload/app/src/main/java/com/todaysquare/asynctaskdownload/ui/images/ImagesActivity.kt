package com.todaysquare.asynctaskdownload.ui.images

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.todaysquare.asynctaskdownload.R

import kotlinx.android.synthetic.main.activity_images.*

import org.jetbrains.anko.toast

import java.lang.Exception
import java.net.URL

class ImagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        buttonDownload.setOnClickListener {
            /*
                * 제약사항
                    1. 한 번 실행한 asyncTask 는 다시 실행 할 수 없고, 새로운 asyncTask 를 생성해 실행해야한다.
                    2. 스케줄링 할 수 있는 작업의 수가 제한되어 있고, 짧은 작업에서만 이상적으로 동작한다.
                    3. Multi thread 작업을 원한다면 asyncTask 실행 시 .THREAD_POOL_EXECUTOR 스케줄러를 지정해야한다.

            */
            //  AsyncTask 구조
            val asyncTask = @SuppressLint("StaticFieldLeak")
            object : AsyncTask<String, Void, Bitmap?>() {
                override fun doInBackground(vararg params: String?): Bitmap? {
                    val urlString = params[0]!!

                    return try {
                        val url = URL(urlString)
                        val stream = url.openStream()

                        BitmapFactory.decodeStream(stream)

                    } catch (error: Exception) {
                        error.printStackTrace()
                        null

                    }
                }

                override fun onProgressUpdate(vararg values: Void?) {
                    super.onProgressUpdate(*values)

                }

                override fun onPostExecute(result: Bitmap?) {
                    super.onPostExecute(result)

                    if (result != null) imagePreview.setImageBitmap(result)
                    else toast("Download failure")

                }
            }

            asyncTask.execute(editUrl.text.toString())

        }
    }
}