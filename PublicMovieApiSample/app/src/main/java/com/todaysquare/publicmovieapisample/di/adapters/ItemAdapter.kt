package com.todaysquare.publicmovieapisample.di.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ItemAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindVIewHolder(holder: RecyclerView.ViewHolder, item: ViewType)

}