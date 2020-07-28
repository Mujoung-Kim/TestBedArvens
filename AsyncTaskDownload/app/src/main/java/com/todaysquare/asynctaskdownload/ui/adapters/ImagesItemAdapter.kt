package com.todaysquare.asynctaskdownload.ui.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.asynctaskdownload.R
import com.todaysquare.asynctaskdownload.data.models.Images

import kotlinx.android.synthetic.main.image_item.view.*

class ImagesItemAdapter : RecyclerView.Adapter<ImagesItemAdapter.ImagesViewHolder>() {
    var imageList = mutableListOf<Images>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.image_item, parent, false)

        return ImagesViewHolder(view)

    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = imageList[position]

        holder.setImage(image)

    }

    inner class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageUri: Uri? = null

        @SuppressLint("SetTextI18n")
        fun setImage(image: Images) {
            itemView.imageAlbum.setImageURI(image.getImageUri())
            itemView.textDisplayName.text = image.displayName
            itemView.textUri.text = image.getImageUri().toString()
            itemView.textContent.text = image.mineType + image.size

        }
    }
}