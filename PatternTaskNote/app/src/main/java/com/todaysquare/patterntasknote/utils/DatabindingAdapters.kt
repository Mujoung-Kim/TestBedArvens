package com.todaysquare.patterntasknote.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.todaysquare.patterntasknote.data.adapters.BachelorAdapter
import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice

@BindingAdapter("setBachelorItems")
fun RecyclerView.setBachelorAdapterItems(items: List<BachelorNotice>?) {
    with((adapter as BachelorAdapter)) {
        this.clear()
        items?.let { this.addItems(it) }
    }
}