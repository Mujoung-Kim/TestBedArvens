package com.todaysquare.patterntasknote.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class InfiniteScrollListener : RecyclerView.OnScrollListener {
    private val startingPageIndex = 0
    private var visibleThreshold = 5
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true
    var mLayoutManager: RecyclerView.LayoutManager

    constructor(layoutManager: LinearLayoutManager) {
        mLayoutManager = layoutManager

    }

    constructor(layoutManager: GridLayoutManager) {
        mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount

    }

    constructor(layoutManager: StaggeredGridLayoutManager) {
        mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount

    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = mLayoutManager.itemCount
        var lastVisibleItemPosition = 0

        if (mLayoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions =
                (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)

            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
        } else if (mLayoutManager is GridLayoutManager)
            lastVisibleItemPosition =
                (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
        else if (mLayoutManager is LinearLayoutManager)
            lastVisibleItemPosition =
                (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount

            if (totalItemCount == 0)
                loading = true

        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount

        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true

        }
    }

    private fun getLastVisibleItem(lastVisibleItemPosition: IntArray): Int {
        var maxSize = 0

        for (i in lastVisibleItemPosition.indices)
            if (i == 0)
                maxSize = lastVisibleItemPosition[i]
            else if (lastVisibleItemPosition[i] > maxSize)
                maxSize = lastVisibleItemPosition[i]

        return maxSize

    }

    fun restState() {
        currentPage = startingPageIndex
        previousTotalItemCount = 0
        loading = true

    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)

}