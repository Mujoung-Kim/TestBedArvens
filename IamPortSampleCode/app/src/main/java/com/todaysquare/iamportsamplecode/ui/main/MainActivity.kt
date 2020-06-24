package com.todaysquare.iamportsamplecode.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.todaysquare.iamportsamplecode.R
import com.todaysquare.iamportsamplecode.databinding.ActivityMainBinding
import com.todaysquare.iamportsamplecode.ui.auth.SmsAuthActivity
import com.todaysquare.iamportsamplecode.utils.Constants.Param.Companion.IMP_UID
import com.todaysquare.iamportsamplecode.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val SMS_AUTH_REQ_CODE = 202

    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        button_to_auth.setOnClickListener {
            startActivityForResult(SmsAuthActivity
                .starterIntent(this, "https://api.iamport.kr/"), SMS_AUTH_REQ_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SMS_AUTH_REQ_CODE) {
            data?.let {
                it.getStringExtra(IMP_UID)?.let { impUid ->
                    mainViewModel.iamPortPostAccessToken(
                        getString(R.string.imp_key), getString(R.string.imp_secret), impUid)

                } ?: toast("The authentication result is missing.\n Re-authentication is required.")
            }
        }
    }
}
