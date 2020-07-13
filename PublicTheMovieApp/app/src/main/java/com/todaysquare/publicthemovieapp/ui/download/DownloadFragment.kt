package com.todaysquare.publicthemovieapp.ui.download

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.todaysquare.publicthemovieapp.MainActivity

import com.todaysquare.publicthemovieapp.R
import com.todaysquare.publicthemovieapp.utils.Constants.Param.Companion.MY_PERMISSION_REQUEST
import com.todaysquare.publicthemovieapp.utils.Constants.Param.Companion.PERMISSIONS
import java.io.IOException

class DownloadFragment : Fragment() {
    private lateinit var downloadManager: DownloadManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasPermissions(context, "$PERMISSIONS"))
            ActivityCompat.requestPermissions(this, PERMISSIONS, MY_PERMISSION_REQUEST)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_download, container, false)

    }

    private fun downloadPoster(contentUrl: String) {

        try {


        } catch (error: IOException) {
            error.printStackTrace()

        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (context != null && permissions != null)
            for (permission in permissions)
                if (ActivityCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED)
                    return false

        return true

    }
}
