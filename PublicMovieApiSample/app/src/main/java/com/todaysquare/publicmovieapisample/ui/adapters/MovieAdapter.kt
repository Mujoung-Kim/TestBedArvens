package com.todaysquare.publicmovieapisample.ui.adapters

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
//import android.support.v4.util.SparseArrayCompat
//import android.support.v7.widget.RecyclerView

import com.todaysquare.publicmovieapisample.data.databases.entites.Movie

import java.util.*

class MovieAdapter(listener: MovieItemAdapter.ViewSelectedListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // MOVIE 혹은 LOADING 아이템의 종류를 파악하기 위해
    private var items: ArrayList<ViewType>

    // 두 종류의 어댑터를 위한 배열 컬렉션
    private var delegateAdapters = SparseArrayCompat<ItemAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType(): Int = AdapterType.LOADING

    }

    init {
        delegateAdapters.put(AdapterType.LOADING, LoadingItemAdapter())
        delegateAdapters.put(AdapterType.MOVIE, MovieItemAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegateAdapters.get(viewType)?.onCreateViewHolder(parent)!!

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))?.onBindVIewHolder(holder, items[position])

    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addMovieList(movieList: List<Movie>) {
        val initPosition = items.size - 1

        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.addAll(movieList)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1)

    }

    fun clearAndAddMovieList(movieList: List<Movie>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(movieList)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)

    }

    @Suppress("CAST_NEVER_SUCCEEDS")
    fun getMovieList(): List<Movie> = items
        .filter { it.getViewType() == AdapterType.MOVIE }
        .map { it as Movie }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

}