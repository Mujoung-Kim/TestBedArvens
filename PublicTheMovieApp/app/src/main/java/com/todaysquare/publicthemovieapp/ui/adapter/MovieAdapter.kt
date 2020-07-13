package com.todaysquare.publicthemovieapp.ui.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.publicthemovieapp.data.models.MovieItem

class MovieAdapter(listener: MovieItemAdapter.ViewSelectedListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ItemAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterType.LOADING
    }

    init {
        delegateAdapters.put(AdapterType.LOADING, LoadingItemAdapter())
        delegateAdapters.put(AdapterType.MOVIE, MovieItemAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)

        //  Log 찍어보기

    }

    //  .onCreateViewHolder(parent) / .onBindViewHolder(holder, items[position]) 변경 점 확인하기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegateAdapters.get(viewType)?.onCreateViewHolder(parent)!!

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))?.onBindViewHolder(holder, items[position])

    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addMovieList(movieList: List<MovieItem>) {
        val initPosition = items.size - 1

        //  초기 위치 제거 및 알림
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        //  모든 목록 추가 후 마지막 로딩용 아이템 추가
        items.addAll(movieList)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1)

    }

    //  초기화 하고 전부 추가하기
    fun clearAndAddMovieList(movieList: List<MovieItem>) {
        items.clear()
        notifyItemRangeInserted(0, getLastPosition())

        items.addAll(movieList)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)

    }

    //  타입별 목록 가져오기
    fun getMovieList(): List<MovieItem> = items
        .filter { it.getViewType() == AdapterType.MOVIE }
        .map { it as MovieItem }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

}