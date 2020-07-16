package com.todaysquare.publicthemovieapp.ui.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.todaysquare.publicthemovieapp.MainActivity
import com.todaysquare.publicthemovieapp.R
import com.todaysquare.publicthemovieapp.TheMoviePopularApp
import com.todaysquare.publicthemovieapp.data.models.MovieList
import com.todaysquare.publicthemovieapp.ui.listeners.InfiniteScrollListener
import com.todaysquare.publicthemovieapp.ui.RxBaseFragment
import com.todaysquare.publicthemovieapp.ui.adapter.MovieAdapter
import com.todaysquare.publicthemovieapp.ui.adapter.MovieItemAdapter
import com.todaysquare.publicthemovieapp.ui.download.DownloadFragment
import com.todaysquare.publicthemovieapp.utils.Constants.Secret.Companion.KEY_THE_MOVIE
import com.todaysquare.publicthemovieapp.utils.Constants.Secret.Companion.KEY_VALUE
import com.todaysquare.publicthemovieapp.utils.androidLazy
import com.todaysquare.publicthemovieapp.utils.inflate

import kotlinx.android.synthetic.main.frag_recycler.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.jetbrains.anko.design.snackbar

import javax.inject.Inject

class MovieFragment : RxBaseFragment(), MovieItemAdapter.ViewSelectedListener {
    @Inject lateinit var movieManager: MovieManager
    private var theMovieList: MovieList? = null
    private var activity = MainActivity()
    private val movieAdapter by androidLazy { MovieAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        TheMoviePopularApp.movieComponent.inject(this)

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.frag_recycler)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_movie_list.apply {
            setHasFixedSize(true)

            val linearLayout = LinearLayoutManager(context)

            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(
                InfiniteScrollListener(
                    { requestMovie() },
                    linearLayout
                )
            )

        }

        if (recycler_movie_list.adapter == null)
            recycler_movie_list.adapter = movieAdapter

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_THE_MOVIE)) {
            theMovieList = savedInstanceState.get(KEY_THE_MOVIE) as MovieList
            movieAdapter.clearAndAddMovieList(theMovieList!!.results)

        } else requestMovie()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val movie = movieAdapter.getMovieList()

        //  Log 찍어서 값 확인 필요
        if (theMovieList != null && movie.isNotEmpty())
            outState.putParcelable(KEY_THE_MOVIE, theMovieList?.copy(results = movie))

    }

    //  fragment screen switching function. -> 화면 붙을 때 동작
    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = getActivity() as MainActivity

    }

    /*override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty())
            recycler_movie_list.snackbar("No URL assigned to this results")
        else {
            val intent = Intent(Intent.ACTION_VIEW)

            intent.data = Uri.parse(url)
            startActivity(intent)

        }
    }*/

    override fun onDownloadItem(url: String?) {
        val downloadFragment = DownloadFragment()
        var bundle = this.arguments

        if (url.isNullOrEmpty())
            recycler_movie_list.snackbar("Not found poster path. check this path")
        else {
            //  add to download link binding function.
            Log.d("Test", url)

            bundle = if (bundle == null) Bundle()
            else Bundle(bundle)
            bundle.putString("PosterPath", url)

            //  fragment to fragment screen switching and dataBinding.
            //  왜 DownloadFragment()로 넘기면 안되는 거지?
            //  bundle data MainActivity 에서 받는 것도 고려
            downloadFragment.arguments = bundle
//            fragmentManager?.beginTransaction()?.replace(R.id.action_container, downloadFragment)
//                ?.addToBackStack(null)?.commitAllowingStateLoss()
            activity.changeFragment(1, bundle)

        }
    }

    private fun requestMovie() {
        job = GlobalScope.launch(Dispatchers.Main) {
            try {
                //  param & theMovieList?.page 값 확인 필요
                val param = mapOf(
                    "page" to (theMovieList?.page).toString(),
                    "api_key" to KEY_VALUE,
                    "sort_by" to "popularity.desc",
                    "language" to "ko"
                )
                //  retrievedMovie 값 확인 필요
                val retrievedMovie = movieManager.getMovieList(param)

                retrievedMovie.page = retrievedMovie.page?.plus(1)
                theMovieList = retrievedMovie

                movieAdapter.addMovieList(retrievedMovie.results)

            } catch (error: Throwable) {
                if (isVisible)
                    recycler_movie_list.snackbar(error.message.orEmpty(), "RETRY") {
                        //  log 사용해 error.message 전문 확인 필요
                        Log.d("MovieTest", error.message.toString())
                        requestMovie()

                    }
            }
        }
    }
}