package com.todaysquare.publicmovieapisample.ui.movies

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.todaysquare.publicmovieapisample.R
import com.todaysquare.publicmovieapisample.di.adapters.MovieAdapter
import com.todaysquare.publicmovieapisample.utils.inflate
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    private val movieManager by lazy { MovieManager() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_movie)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView_movie_list.apply {
            setHasFixedSize(true)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                layoutManager = LinearLayoutManager(context)

        }

        if (recyclerView_movie_list.adapter == null)
            recyclerView_movie_list.adapter = MovieAdapter()

        requestMovie()

    }

    private fun requestMovie() {
        val subscription = movieManager.getMovieList()
            .subscribeOn(Schedulers.io())
            .subscribe({ retrievedMovie ->
                (recyclerView_movie_list.adapter as MovieAdapter).addMovieList(retrievedMovie)
            }, { error ->
                recyclerView_movie_list.snackbar(error.message ?: "")

            })
    }
}
