package com.todaysquare.publicmovieapisample.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.google.android.material.snackbar.Snackbar
import com.todaysquare.publicmovieapisample.R

import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolBar)

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }

        if (savedInstanceState == null)
            changeFragment(MovieFragment())

    }

    // 프레그먼트 가이드: https://developer.android.com/guide/components/fragments?hl=ko
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager

        if (fragmentManager.backStackEntryCount > 1) fragmentManager.popBackStack()
        else finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)

        }

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.base_content, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    fun clearBackStack() {
        val manager = supportFragmentManager

        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)

            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        }
    }
}
