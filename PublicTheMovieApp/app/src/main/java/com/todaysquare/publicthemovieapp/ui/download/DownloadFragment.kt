package com.todaysquare.publicthemovieapp.ui.download

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.todaysquare.publicthemovieapp.R
import com.todaysquare.publicthemovieapp.utils.loading

import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.imageURI

import org.jetbrains.anko.support.v4.toast

import java.io.FileNotFoundException
import java.io.IOException

class DownloadFragment : Fragment() {
    companion object {
        const val TAG = "DownTest"

    }
    private lateinit var downloadManager: DownloadManager
    private var downloadID: Long = 0
    private var posterUrl: String? = null
    private var fileName: String? = null
    private var posterPath: String? = null
    private var intentFilter = IntentFilter()
    private val downloadComplete = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == intent.action) {
                if (downloadID == id) {
                    val query = DownloadManager.Query()

                    query.setFilterById(id)

                    val cursor = downloadManager.query(query)

                    if (!cursor.moveToNext()) return

                    val columnsIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    val status = cursor.getInt(columnsIndex)

                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        try {
                            Log.d(TAG, "status = $status")
                            Log.d(TAG, "DownloadManager = ${DownloadManager.STATUS_SUCCESSFUL}")
                            showPoster()

                        } catch (error: FileNotFoundException) {
                            error.printStackTrace()

                        } catch (error: IOException) {
                            error.printStackTrace()

                        }
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        downloadManager.remove(downloadID)
                        downloadPoster()

                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_download, container, false)
        val split = this.arguments?.getString("PosterPath")?.split("/")

        posterUrl = this.arguments?.getString("PosterPath")
        fileName = split?.get(split.size - 1) ?: "cannot binding data"
        Log.d(TAG, "split = $split")
        Log.d(TAG, "fileName = $fileName")

        if (posterUrl == null)
            toast("dataBinding failed. please check connection.")
        else
            downloadPoster()

        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        context?.registerReceiver(downloadComplete, intentFilter)

        // Inflate the layout for this fragment
        return view

    }

    private fun downloadPoster() {
        downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        try {
            val posterUrl = Uri.parse(posterUrl)

            Log.d(TAG, posterUrl.toString())
            Log.d(TAG, "fileName = $fileName")

            val request = DownloadManager.Request(posterUrl)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

            downloadID = downloadManager.enqueue(request)

        } catch (error: IOException) {
            error.printStackTrace()

        }
    }

    //  start function after download complete.
    private fun showPoster() {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.IS_PENDING)
        val sortOrder = "${MediaStore.Images.Media._ID} ASC"
        val cursor = context?.contentResolver?.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection, null, null, sortOrder)
            ?.use {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val typeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val imagePoster = view?.findViewById<ImageView>(R.id.image_poster)

                if (it.moveToNext()) {
                    val id = it.getInt(idColumn)
                    val display = it.getString(displayColumn)
                    val type = it.getString(typeColumn)
                    val size = it.getLong(sizeColumn)

                    posterPath = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id.toString()).toString()
                    Log.d(TAG, "id = $id, display = $display, type = $type, size = $size, posterUri = $posterPath")
                    imagePoster?.loading(posterPath.toString())
                    Log.d(TAG, "posterPath ${posterPath.toString()}")

                }
            }

        return cursor!!

    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(downloadComplete)

    }
}
