package com.todaysquare.publicmovieapisample.di.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.publicmovieapisample.R
import com.todaysquare.publicmovieapisample.utils.inflate

class LoadingItemAdapter : ItemAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = LoadingViewHolder(parent)

    override fun onBindVIewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }
    class LoadingViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.item_loading))

}