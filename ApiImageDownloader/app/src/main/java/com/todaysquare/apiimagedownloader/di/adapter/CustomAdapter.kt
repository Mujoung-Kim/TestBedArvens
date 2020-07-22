package com.todaysquare.apiimagedownloader.di.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.apiimagedownloader.R
import com.todaysquare.apiimagedownloader.data.network.response.ApiModels

import kotlinx.android.synthetic.main.item_test.view.*

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {
    companion object {
        const val TAG = "Adapter Test"
    }
    val userList = mutableListOf<ApiModels>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_test, parent, false)

        return Holder(view)

    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userList[position]

        holder.showData(user)

    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showData(user: ApiModels) {
            Log.d(TAG, "${user.data}")

            itemView.textView.text = user.data.toString()

        }
    }
}