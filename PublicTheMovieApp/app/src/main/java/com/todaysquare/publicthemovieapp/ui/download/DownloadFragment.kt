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

import com.todaysquare.publicthemovieapp.R
import com.todaysquare.publicthemovieapp.ui.adapter.LoadingItemAdapter
import com.todaysquare.publicthemovieapp.utils.loading

import kotlinx.android.synthetic.main.frag_download.*

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
    private var posterName: String? = null
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
//                            Log.d(TAG, "status = $status")
//                            Log.d(TAG, "DownloadManager = ${DownloadManager.STATUS_SUCCESSFUL}")
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
        Log.d(TAG, "splitM = $split")
        Log.d(TAG, "fileNameM = $fileName")

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
            checkFile()
            Log.d(TAG, "fileNameD = $fileName")
            Log.d(TAG, "displayNameD = $posterName")
            Log.d(TAG, "posterPathD = $posterPath")

            if (fileName != posterName) {
                val posterUri = Uri.parse(posterUrl)

                Log.d(TAG, posterUri.toString())

                val request = DownloadManager.Request(posterUri)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

                downloadID = downloadManager.enqueue(request)

            } else if (fileName == posterName)
                show_poster.loading(posterPath.toString())

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

                while (it.moveToNext()) {
                    val id = it.getInt(idColumn)
                    val display = it.getString(displayColumn)
                    val type = it.getString(typeColumn)
                    val size = it.getLong(sizeColumn)
                    val posterUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id.toString()).toString()

                    Log.d(TAG, "id = $id, display = $display, type = $type, size = $size, posterUri = $posterUri")
                    /*if (posterUri == posterPath) {
                        posterPath = posterUri

                        Log.d(TAG, "posterUriS = $posterUri")
                        Log.d(TAG, "posterPathS = $posterPath")
                        show_poster.loading(posterPath.toString())

                    } else*/
                        show_poster.loading(posterUri)

                }
            }

        return cursor!!

    }

    private fun checkFile() {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME)
//        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} == $fileName"
        val sortOrder = "${MediaStore.Images.Media._ID} ASC"
        val cursor = context?.contentResolver?.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection, /*selection*/null, null, sortOrder)
            ?.use {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                while (it.moveToNext()) {
                    val id = it.getInt(idColumn)
                    val display = it.getString(displayColumn)

                    Log.d(TAG, "id = $id, display = $display")
                    if (display == fileName) {
                        posterName = display
                        posterPath = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            id.toString()).toString()

                        Log.d(TAG, "displayNameC = $display")
                        Log.d(TAG, "posterNameC = $posterName")
                        Log.d(TAG, "posterPathC = $posterPath")

                    }
                }
            }

        return cursor!!

    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(downloadComplete)

    }
}
