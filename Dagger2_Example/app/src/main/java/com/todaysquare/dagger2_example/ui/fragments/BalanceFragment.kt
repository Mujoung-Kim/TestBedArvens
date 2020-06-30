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
import com.todaysquare.dagger2_example.databinding.FragmentBalanceBinding
import com.todaysquare.dagger2_example.ui.viewmodels.TransactionViewModel

import javax.inject.Inject

class BalanceFragment : Fragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var sharedPreferences: SharedPreferences
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var fragmentBalanceBinding: FragmentBalanceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        (activity!!.application as BaseApplication).getSharedComponent().inject(this)

        fragmentBalanceBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_balance, container, false)
        // Inflate the layout for this fragment
        return fragmentBalanceBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiKey = sharedPreferences.getString("APIKEY", "0000") ?: ""

        transactionViewModel =
            ViewModelProvider(this, viewModelFactory)[TransactionViewModel::class.java]


        transactionViewModel.errorMessage().observe(this, Observer { error ->
            fragmentBalanceBinding.errorTxt.text = error

        })

        transactionViewModel.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) fragmentBalanceBinding.progressBar.visibility = View.VISIBLE
            else fragmentBalanceBinding.progressBar.visibility = View.INVISIBLE

        })

        transactionViewModel.getTransactionMutableLiveData(apiKey, "sent")
            .observe(this, Observer { data ->
                Log.d("Transaction", data.toString())

        })

    }
}
