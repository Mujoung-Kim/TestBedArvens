package com.todaysquare.patterntasknote.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import com.google.firebase.analytics.FirebaseAnalytics
import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.databinding.ActivityCMainBinding
import com.todaysquare.patterntasknote.ui.base.BaseActivity

class CMainActivity : BaseActivity<ActivityCMainBinding>(R.layout.activity_c_main) {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        initNavigation()

    }

    private fun initNavigation() {
        val navController = findNavController(R.id.main_nav_host)

        binding.mainBottomNavigation.setupWithNavController(navController)

    }
}