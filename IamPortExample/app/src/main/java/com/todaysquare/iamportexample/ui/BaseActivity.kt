package com.todaysquare.iamportexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.todaysquare.iamportexample.R
import com.todaysquare.iamportexample.databinding.ActivityBaseBinding
import com.todaysquare.iamportexample.ui.main.MainViewModel
import com.todaysquare.iamportexample.ui.main.MainViewModelFactory
import com.todaysquare.iamportexample.utils.Constants.Tag.Companion.BASE_ACTIVITY
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.coroutines.launch

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class BaseActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()

    private val factory by instance<MainViewModelFactory>()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityBaseBinding>(this, R.layout.activity_base)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        getAccessToken()
//        binding.lifecycleOwner = this
//        Log.d(BASE_ACTIVITY
//            , "key - ${getString(R.string.imp_key)},\nsecret - ${getString(R.string.imp_secret)}")
//        binding.viewModel = viewModel
//
    }

    private fun getAccessToken() {
        lifecycleScope.launch {
            val authResult = viewModel.getAccessToken()

            body_text.text = authResult.tokenResponse.toString()

        }
    }
}
