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

import com.google.android.material.snackbar.Snackbar
import com.todaysquare.dagger2_example.BaseApplication
import com.todaysquare.dagger2_example.R
import com.todaysquare.dagger2_example.databinding.FragmentAccountBinding
import com.todaysquare.dagger2_example.ui.viewmodels.AccountViewModel

import javax.inject.Inject

class AccountFragment : Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var sharedPreferences: SharedPreferences
    private lateinit var accountViewModel: AccountViewModel
    private lateinit var fragmentAccountBinding: FragmentAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        (activity!!.application as BaseApplication).getSharedComponent().inject(this)

        fragmentAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        // Inflate the layout for this fragment
        return fragmentAccountBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiKey = sharedPreferences.getString("APIKEY", "0000") ?: ""

        accountViewModel = ViewModelProvider(this, viewModelFactory)[AccountViewModel::class.java]

        accountViewModel.errorDisplay().observe(this, Observer { error ->
            fragmentAccountBinding.errorTxt.text = error

        })

        accountViewModel.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) fragmentAccountBinding.progressBar.visibility = View.VISIBLE
            else fragmentAccountBinding.progressBar.visibility = View.INVISIBLE

        })

        fragmentAccountBinding.btnSent.setOnClickListener {
            if (fragmentAccountBinding.editAddress.text.isEmpty()
                && fragmentAccountBinding.editAddress.text.isEmpty())
                Snackbar.make(it, getString(R.string.enter_address), Snackbar.LENGTH_LONG).show()
            else {
                accountViewModel.getWithDrawCoinRepositoryLiveData(apiKey,
                    fragmentAccountBinding.editAmount.text.toString(),
                    fragmentAccountBinding.editAddress.text.toString())
                    .observe(this, Observer { data ->
                        Log.e("Account", data.status)

                    })

            }
        }
    }
}
