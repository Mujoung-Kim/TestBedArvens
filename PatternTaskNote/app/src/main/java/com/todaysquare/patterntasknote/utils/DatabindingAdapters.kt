package com.todaysquare.patterntasknote.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.todaysquare.patterntasknote.data.adapters.BachelorAdapter
import com.todaysquare.patterntasknote.data.adapters.FavoriteAdapter
import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice
import com.todaysquare.patterntasknote.ui.bachelor.BachelorNoticeViewModel

@BindingAdapter("setBachelorItems")
fun RecyclerView.setBachelorAdapterItems(items: List<BachelorNotice>?) {
    with((adapter as BachelorAdapter)) {
        this.clear()
        items?.let { this.addItems(it) }
    }
}

@BindingAdapter("setFavoriteItems")
fun RecyclerView.setFavoriteAdapterItems(items: List<FavoriteNotice>?) {
    with((adapter as FavoriteAdapter)) {
        this.clear()
        items?.let { this.addItem(it) }
    }
}

@BindingAdapter("bachelorInfiniteScroll")
fun RecyclerView.setBachelorInfiniteScroll(viewModel: BachelorNoticeViewModel) {
    val scrollListener = object : InfiniteScrollListener(layoutManager as LinearLayoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
            viewModel.requestMoreNotice(totalItemsCount + 1)

        }
    }

    this.addOnScrollListener(scrollListener)

}