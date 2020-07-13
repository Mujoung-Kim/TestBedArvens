package com.todaysquare.publicthemovieapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ItemAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)

}