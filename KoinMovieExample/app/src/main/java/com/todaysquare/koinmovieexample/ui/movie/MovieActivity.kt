package com.todaysquare.koinmovieexample.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.squareup.picasso.Picasso
import com.todaysquare.koinmovieexample.R
import com.todaysquare.koinmovieexample.data.network.MovieAdapter
import com.todaysquare.koinmovieexample.utils.GridSpacingItemDecoration

import kotlinx.android.synthetic.main.activity_main.*

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModel()
    private val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MovieAdapter(picasso)

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MovieActivity, 2)
            addItemDecoration(GridSpacingItemDecoration(2, 30, true))
            this.adapter = adapter

        }

        movieViewModel.uiState.observe(this, Observer {
            val dataState = it ?: return@Observer

            progress_bar.visibility = if (dataState.showProgress) View.VISIBLE else View.GONE

            if (dataState.movies != null && !dataState.movies.consumed)
                dataState.movies.consume()?.let { movies ->
                    println(movies)
                    adapter.submitList(movies)

                }
            if (dataState.error != null && !dataState.error.consumed)
                dataState.error.consume()?.let { errorResource ->
                    Toast.makeText(this, resources.getString(errorResource), Toast.LENGTH_SHORT).show()

                }

        })

    }
}
