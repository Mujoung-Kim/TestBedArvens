package com.todaysquare.koinmovieexample.data.network

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.squareup.picasso.Picasso
import com.todaysquare.koinmovieexample.data.MovieCollection
import com.todaysquare.koinmovieexample.ui.movie.MovieAdapterViewHolder

class MovieAdapter(private val picasso: Picasso)
    : ListAdapter<MovieCollection.Movie, MovieAdapterViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<MovieCollection.Movie>() {
                override fun areItemsTheSame(oldItem: MovieCollection.Movie,
                    newItem: MovieCollection.Movie): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: MovieCollection.Movie,
                    newItem: MovieCollection.Movie): Boolean = oldItem == newItem

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder =
        MovieAdapterViewHolder.create(parent, picasso)

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
}