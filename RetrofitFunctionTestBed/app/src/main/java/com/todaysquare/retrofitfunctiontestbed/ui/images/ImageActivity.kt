package com.todaysquare.retrofitfunctiontestbed.ui.images

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.todaysquare.retrofitfunctiontestbed.R
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Param.Companion.CAMERA_PERMISSION
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Param.Companion.FLAG_PERM_CAMERA
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Param.Companion.FLAG_PERM_STORAGE
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Param.Companion.FLAG_REQ_CAMERA
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Param.Companion.FLAG_REQ_STORAGE
import com.todaysquare.retrofitfunctiontestbed.utils.Constants.Param.Companion.STORAGE_PERMISSION

import kotlinx.android.synthetic.main.activity_image.*

import org.jetbrains.anko.toast

class ImageActivity : AppCompatActivity() {
    private val viewModel: ImageViewModel by viewModels() // { imageViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        if (checkPermission(STORAGE_PERMISSION, FLAG_PERM_STORAGE))
            setView()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                FLAG_REQ_CAMERA -> {
                    if (data?.extras?.get("data") != null) {
                        val bitmap = data.extras?.get("data") as Bitmap
                        val uri = viewModel.saveImageFile(viewModel.newFileName(),
                            "image/jpg", bitmap)

                        imagePreview.setImageURI(uri)

                    }
                }
                FLAG_REQ_STORAGE -> {
                    val uri = data?.data

                    imagePreview.setImageURI(uri)

                }
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {
        when (requestCode) {
            FLAG_PERM_STORAGE -> {
                for (grant in grantResults)
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        toast("You need to approve storage permissions, but you can use the app.")
                        finish()

                        return
                    }
                setView()

            }

            FLAG_PERM_CAMERA -> {
                for (grant in grantResults)
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        toast("You need to approve camera permissions, but you can use the camera.")

                        return

                    }
                openCamera()

            }
        }
    }

    private fun setView() {
        buttonCamera.setOnClickListener {
            openCamera()

        }

        buttonGallery.setOnClickListener {
            openGallery()

        }
    }

    private fun openCamera() {
        if (checkPermission(CAMERA_PERMISSION, FLAG_REQ_CAMERA)) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(intent, FLAG_REQ_CAMERA)

        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)

        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, FLAG_REQ_STORAGE)

    }

    @SuppressLint("ObsoleteSdkInt")
    private fun checkPermission(permissions: Array<out String>, flag: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            for (permission in permissions)
                if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, flag)

                    return false

                }
        return true

    }
}