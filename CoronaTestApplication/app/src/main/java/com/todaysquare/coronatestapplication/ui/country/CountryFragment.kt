package com.todaysquare.coronatestapplication.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.todaysquare.coronatestapplication.R
import com.todaysquare.coronatestapplication.databinding.FragmentCountryBinding

/*
    *   Model-View-viewModel 패턴 작업순서
        1. Model part
            *   entity -> database (entity, dao, appDatabase, models)
            *   useCase -> entity flow 를 말한다.
            *   api -> retrofit || socket communication (response, restFullBody, apiInterface)
            *   repository -> local && remote dataSource (impl etc ...)
        2. viewModel part
        3. View part
        4. di
*/
class CountryFragment : Fragment() {
    private lateinit var binding: FragmentCountryBinding
    private val viewModel: CountryViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModelCallback()
        viewModel.requestCountry()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    private fun initViewModelCallback() {
        with(viewModel) {
            toastMsg.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, getString(R.string.network_err_toast_msg), Toast.LENGTH_SHORT).show()

            })

        }
    }
}