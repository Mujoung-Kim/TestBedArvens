package com.todaysquare.coronatestapplication.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.todaysquare.coronatestapplication.R
import com.todaysquare.coronatestapplication.databinding.FragmentCityBinding

class CityFragment : Fragment() {
    private lateinit var binding: FragmentCityBinding
    private val viewModel: CityViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }
}