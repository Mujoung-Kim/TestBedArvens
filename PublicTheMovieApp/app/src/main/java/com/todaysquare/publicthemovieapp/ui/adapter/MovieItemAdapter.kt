package com.todaysquare.publicthemovieapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.publicthemovieapp.R
import com.todaysquare.publicthemovieapp.data.models.MovieItem
import com.todaysquare.publicthemovieapp.ui.download.DownloadFragment
import com.todaysquare.publicthemovieapp.utils.Constants.Url.Companion.IMAGE_URL
import com.todaysquare.publicthemovieapp.utils.inflate
import com.todaysquare.publicthemovieapp.utils.loading

import kotlinx.android.synthetic.main.item_movie.view.*

import org.jetbrains.anko.design.snackbar

class MovieItemAdapter(val viewActions: ViewSelectedListener) : ItemAdapter {

    interface ViewSelectedListener {
        fun onItemSelected(url: String?)
//        fun onDownloadItem(url: String?)

    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        MovieViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MovieViewHolder
        holder.bind(item as MovieItem)

    }

    inner class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.item_movie)) {

        private val moviePoster = itemView.image_poster
        private val overview = itemView.text_overview
        private val releaseDate = itemView.text_release_date
        private val voteCount = itemView.text_vote_count
        private val textTitle = itemView.text_title
        private val textAverage = itemView.rate_vote_avg
        private val buttonReserve = itemView.button_reserve

        fun bind(item: MovieItem) {
            moviePoster.loading(IMAGE_URL + item.poster_path)
            overview.text = item.overview
            releaseDate.text = item.release_date
            voteCount.text = "${item.vote_count} Votes"
            textTitle.text = item.title
            textAverage.rating = item.vote_average / 2

            super.itemView.setOnClickListener {
                viewActions.onItemSelected(IMAGE_URL + item.poster_path)
//                viewActions.onDownloadItem(IMAGE_URL + item.poster_path)

            }

            buttonReserve.setOnClickListener {
                it.snackbar("This is snackBar. Okay?")

            }
        }
    }
}