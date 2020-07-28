package com.todaysquare.asynctaskdownload.ui.images

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.todaysquare.asynctaskdownload.R
import com.todaysquare.asynctaskdownload.ui.adapters.ImagesItemAdapter
import kotlinx.android.synthetic.main.activity_media_store_images.*
import org.jetbrains.anko.toast

class MediaStoreImagesActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val viewModel: ImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {

        if (requestCode == 99) {
            var check = true

            for (grant in grantResults)
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    check = false
                    break
                }

            if (!check) {
                toast("You need to approve all permission requests, but you can make the app live.")
                finish()

            } else startProcess()

        }
    }

    fun startProcess() {
        setContentView(R.layout.activity_media_store_images)

        val adapter = ImagesItemAdapter()

        adapter.imageList.addAll(viewModel.getImageList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, permissions.toString())
            != PackageManager.PERMISSION_GRANTED) requestPermission()
        else startProcess()

    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 99)

    }
}