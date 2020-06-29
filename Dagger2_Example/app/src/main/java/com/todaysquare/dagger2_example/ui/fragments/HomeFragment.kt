package com.todaysquare.dagger2_example.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.todaysquare.dagger2_example.BaseApplication
import com.todaysquare.dagger2_example.R
import com.todaysquare.dagger2_example.databinding.FragmentHomeBinding
import com.todaysquare.dagger2_example.ui.viewmodels.HomeViewModel

import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var sharedPreferences: SharedPreferences
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity!!.application as BaseApplication).getSharedComponent().inject(this)

        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return fragmentHomeBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiKey = sharedPreferences.getString("APIKEY","0000000")

        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        homeViewModel.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) fragmentHomeBinding.progressBar.visibility = View.VISIBLE
            else fragmentHomeBinding.progressBar.visibility = View.INVISIBLE

        })

        homeViewModel.errorDisplay().observe(this, Observer { error ->
            Log.e("Home", error.toString())

        })

        homeViewModel

    }
}
