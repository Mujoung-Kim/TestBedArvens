package com.todaysquare.publicmovieapisample.ui.movies

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.snackbar.Snackbar
import com.todaysquare.publicmovieapisample.R
import com.todaysquare.publicmovieapisample.base.TheMoviePopularApp
import com.todaysquare.publicmovieapisample.data.databases.entites.MovieList
import com.todaysquare.publicmovieapisample.ui.InfiniteScrollListener
import com.todaysquare.publicmovieapisample.ui.adapters.MovieAdapter
import com.todaysquare.publicmovieapisample.ui.adapters.MovieItemAdapter
import com.todaysquare.publicmovieapisample.ui.bases.RxBaseFragment
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.API_KEY
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.KEY_THE_MOVIE
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.LANGUAGE
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.PAGE
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.SORT_BY
import com.todaysquare.publicmovieapisample.utils.Constants.Param.Companion.SORT_POPULARITY
import com.todaysquare.publicmovieapisample.utils.Constants.Secret.Companion.KEY_VALUE
import com.todaysquare.publicmovieapisample.utils.androidLazy
import com.todaysquare.publicmovieapisample.utils.inflate

import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.design.snackbar

import javax.inject.Inject

class MovieFragment : RxBaseFragment(), MovieItemAdapter.ViewSelectedListener {
    @Inject lateinit var movieManager: MovieManager
    private val movieAdapter by androidLazy { MovieAdapter(this) }
    private var theMovieList: MovieList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  컴포넌트에 의해 의존성 모듈이 주입된다.
        TheMoviePopularApp.movieComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_movie)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView_movie_list.apply {
            setHasFixedSize(true)

            val linearLayout = LinearLayoutManager(context)

            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestMovie() }, linearLayout))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                layoutManager = LinearLayoutManager(context)

        }

        if (recyclerView_movie_list.adapter == null)
            recyclerView_movie_list.adapter = movieAdapter

        if (savedInstanceState != null && savedInstanceState.containsKey((KEY_THE_MOVIE))) {
            theMovieList = savedInstanceState.get(KEY_THE_MOVIE) as MovieList
            movieAdapter.clearAndAddMovieList(theMovieList!!.results)

        } else
            requestMovie()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val movie = movieAdapter.getMovieList()

        if (theMovieList != null && movie.isNotEmpty())
            outState.putParcelable(KEY_THE_MOVIE, theMovieList?.copy(results = movie))

    }

    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty())
            recyclerView_movie_list.snackbar("No URL assigned to this results")
        else {
            val intent = Intent(Intent.ACTION_VIEW)

            intent.data = Uri.parse(url)
            startActivity(intent)

        }
    }

    /*private fun requestMovie() {
        val subscription = movieManager.getMovieList((theMovieList?.page).toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ retrievedMovie ->
                theMovieList = retrievedMovie
                (recyclerView_movie_list.adapter as MovieAdapter).addMovieList(retrievedMovie)

            }, { error ->
                Snackbar.make(recyclerView_movie_list, error.message ?: "", Snackbar.LENGTH_LONG).show()
//                recyclerView_movie_list.snack(error.message ?: "")

            })

        subscriptions.add(subscription)

    }*/

    private fun requestMovie() {
        job = GlobalScope.launch(Dispatchers.Main) {
            try {
                val param = mapOf(
                    PAGE to (theMovieList?.page).toString(),
                    API_KEY to KEY_VALUE,
                    SORT_BY to SORT_POPULARITY,
                    LANGUAGE to "ko")
                val retrievedMovie = movieManager.getMovieList(param)

                retrievedMovie.page = retrievedMovie.page?.plus(1)
                theMovieList = retrievedMovie

                movieAdapter.addMovieList(retrievedMovie.results)

            } catch (error: Throwable) {
                recyclerView_movie_list.snackbar(error.message.orEmpty(), "RETRY") {
                    requestMovie()

                }
            }
        }
    }
}
