package com.todaysquare.restapiexample.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.todaysquare.restapiexample.R

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemID) {
            R.id.navigation_contacts -> {
                supportFragmentManager.beginTransaction().hide(activityFragment).show(contactsFragment).commit()
                activeFragment = contactsFragment

                if (supportActionBar != null)
                    supportActionBar!!.title = getString(R.string.title_contacts)
                return@OnNavigationItemSelectedListener true

            }

            R.id.navigation_chats -> {
                supportFragmentManager.beginTransaction().hide(activeFragment).show(chatsFragment).commit()
                activeFragment = chatsFragment

                if (supportActionBar != null)
                    supportActionBar!!.title = getString(R.string.title_chats)
                return@OnNavigationItemSelectedListener true

            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportActionBar != null)
            supportActionBar!!.title = getString(R.string.title_contacts)

        setContentView(R.layout.activity_main)

    }
}
