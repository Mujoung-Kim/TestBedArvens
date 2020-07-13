package com.todaysquare.publicthemovieapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.publicthemovieapp.R
import com.todaysquare.publicthemovieapp.utils.inflate

class LoadingItemAdapter : ItemAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }

    class LoadingViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(parent.inflate(R.layout.item_loading))

}