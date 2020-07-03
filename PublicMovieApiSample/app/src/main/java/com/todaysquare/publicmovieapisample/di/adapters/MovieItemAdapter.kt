package com.todaysquare.publicmovieapisample.di.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.publicmovieapisample.R
import com.todaysquare.publicmovieapisample.data.databases.entites.Movie
import com.todaysquare.publicmovieapisample.utils.inflate
import com.todaysquare.publicmovieapisample.utils.loadImg

import kotlinx.android.synthetic.main.item_movie_content.view.*

class MovieItemAdapter : ItemAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MovieViewHolder(parent)

    @Suppress("CAST_NEVER_SUCCEEDS")
    override fun onBindVIewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MovieViewHolder
        holder.bind(item as Movie)

    }

    inner class MovieViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(parent.inflate(R.layout.item_movie_content)) {

        private val moviePoster = itemView.imageView_poster
        private val movieTitle = itemView.textView_title
        private val voteCount = itemView.textView_vote_count
        private val releaseDate = itemView.textView_release_data
        private val overView = itemView.textView_overview
        private val rateAverage = itemView.rate_vote_average
        private val rateReserve = itemView.button_reserve

        @SuppressLint("SetTextI18n")
        fun bind(item: Movie) {
            moviePoster.loadImg(item.poster_path)
            movieTitle.text = item.title
            voteCount.text = "${item.vote_count} 투표"
            releaseDate.text = item.release_data
            overView.text = item.overview
            rateAverage.rating = item.vote_average

        }
    }
}