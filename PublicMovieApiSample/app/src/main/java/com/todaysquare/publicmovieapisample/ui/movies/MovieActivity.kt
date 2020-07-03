package com.todaysquare.publicmovieapisample.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.todaysquare.publicmovieapisample.R

import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolBar)

        if (savedInstanceState == null)
            changeFragment(MovieFragment())

    }

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.base_content, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
}
