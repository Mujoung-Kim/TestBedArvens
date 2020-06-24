package com.todaysquare.iamportsamplecode.ui.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil

import com.todaysquare.iamportsamplecode.R
import com.todaysquare.iamportsamplecode.databinding.ActivitySmsAuthBinding
import com.todaysquare.iamportsamplecode.ui.SmsAuthFactory
import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.AND_BRIDEGE
import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.WEB_VIEW

import kotlinx.android.synthetic.main.activity_sms_auth.*

class SmsAuthActivity : AppCompatActivity() {
    companion object {
        fun starterIntent(context: Context, url: String): Intent {
            return Intent(context, SmsAuthActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(WEB_VIEW, url)

            }
        }
    }
    private val smsViewModel: SmsAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivitySmsAuthBinding>(this, R.layout.activity_sms_auth)

        binding.viewModel = smsViewModel
        binding.lifecycleOwner = this

        initWebView()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        smsViewModel.webView.apply {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && this.canGoBack()) {
                this.goBack()

                return true

            }
        }
        return super.onKeyDown(keyCode, event)

    }

    private fun initWebView() {
        smsViewModel.applyWebView(webView)

        val url = intent.getStringExtra(WEB_VIEW)

        smsViewModel.setUrl(url)

        webView.addJavascriptInterface(SmsAuthFactory(this), AND_BRIDEGE)

    }
}
