package com.todaysquare.publicthemovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.google.android.material.snackbar.Snackbar
import com.todaysquare.publicthemovieapp.ui.download.DownloadFragment
import com.todaysquare.publicthemovieapp.ui.movie.MovieFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }

//        Log.d("Test", "MainActivity = $savedInstanceState")
        if (savedInstanceState == null)
            changeFragment()

    }

    //  이 부분 수정해서 fragmentA -> fragmentB 로 전환 ==> 수정완료.
    fun changeFragment(fragmentNum: Int = 0) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        Log.d("MainTest", fragmentNum.toString())

        if (fragmentNum == 0) fragmentTransaction.replace(R.id.action_container, MovieFragment())
                .addToBackStack(null).commitAllowingStateLoss()
        else if (fragmentNum == 1)
            fragmentTransaction.replace(R.id.action_container, DownloadFragment())
            .addToBackStack(null).commitAllowingStateLoss()

    }

    fun clearBackStack() {
        val manager = supportFragmentManager

        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)

            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager

        if (fragmentManager.backStackEntryCount > 1)
            fragmentManager.popBackStack()
        else
            finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)

        }
    }
}
