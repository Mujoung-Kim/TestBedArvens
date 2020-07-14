package com.todaysquare.publicthemovieapp.ui.download

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.todaysquare.publicthemovieapp.MainActivity

import com.todaysquare.publicthemovieapp.R
import com.todaysquare.publicthemovieapp.ui.adapter.MovieItemAdapter
import com.todaysquare.publicthemovieapp.utils.Constants.Param.Companion.MY_PERMISSION_REQUEST
import com.todaysquare.publicthemovieapp.utils.Constants.Param.Companion.PERMISSIONS
import kotlinx.android.synthetic.main.item_movie.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.toast
import java.io.IOException

class DownloadFragment : Fragment() {
    companion object {
        const val TAG = "DownTest"

        @JvmStatic
        fun newInstance(key: String): DownloadFragment =
            DownloadFragment().apply {
                arguments = Bundle().apply {
                    putString("PosterPath", key)
                }
            }
    }
    private lateinit var downloadManager: DownloadManager
    private var test: String? = null
    private val receiveData by lazy { requireArguments().getString("PosterPath") }
//    private val split = arguments?.getString("PosterPath")!!.split("/")
//    private val fileName = split[split.size - 1]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            test = it.getString("PosterPath")

        }
//        Log.d(TAG, receiveData.toString())

        if (test == null)
            toast("dataBinding failed. please check connection.")
        else
            downloadPoster()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_download, container, false)

    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d(TAG, arguments?.getString("PosterPath").toString())
//
//    }

    private fun downloadPoster() {
        downloadManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        try {
            val posterUrl = Uri.parse(receiveData)

            Log.d(TAG, posterUrl.toString())
//            Log.d(TAG, fileName)

            val request = DownloadManager.Request(posterUrl)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "test"/*fileName*/)

            downloadManager.enqueue(request)

        } catch (error: IOException) {
            error.printStackTrace()

        }
    }

    //  start function after download complete.
    private fun showPoster() {
        //  media provider 이용 저장된 이미지로 변경
//        image_poster.setImageResource(directory)

    }
}
