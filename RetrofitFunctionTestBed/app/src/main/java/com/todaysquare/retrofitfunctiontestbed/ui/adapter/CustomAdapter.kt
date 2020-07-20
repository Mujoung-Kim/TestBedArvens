package com.todaysquare.retrofitfunctiontestbed.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.todaysquare.retrofitfunctiontestbed.R
import com.todaysquare.retrofitfunctiontestbed.data.repository.RepositoryItem

import kotlinx.android.synthetic.main.item_recycler.view.*

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {
    var userList = mutableListOf<RepositoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)

        return Holder(view)

    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userList[position]

        holder.setUser(user)

    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setUser(user: RepositoryItem) {
            itemView.textName.text = user.name
            itemView.textId.text = user.node_id

            Glide.with(itemView)
                .load(user.owner.avatar_url)
                .centerCrop()
                .into(itemView.imageAvatar)

        }
    }
}