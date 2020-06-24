package com.todaysquare.iamportsamplecode.ui.auth

import android.annotation.SuppressLint
import android.app.Application
import android.net.http.SslError
import android.os.Build
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.lifecycle.AndroidViewModel

import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.ERROR
import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.ON_PROGRESS
import com.todaysquare.iamportsamplecode.utils.Constants.String.Companion.SET_JAVA_SCRIPT

class SmsAuthViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var webView: WebView

    fun applyWebView(webView: WebView) { this.webView = webView }
    fun setUrl(url: String) { setWebViewSetting(this.webView, url) }

    @SuppressLint(SET_JAVA_SCRIPT)
    fun setWebViewSetting(webView: WebView, url: String) {
        webView.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            settings.cacheMode = WebSettings.LOAD_NO_CACHE

            webChromeClient = myChromeClient()
            webViewClient = WebViewClientClass()

            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
            isScrollbarFadingEnabled = true

            if (Build.VERSION.SDK_INT >= 19) setLayerType(View.LAYER_TYPE_HARDWARE, null)
            else setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            loadUrl(url)

        }
    }

    private inner class myChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            Log.d(ON_PROGRESS, "$newProgress")
            super.onProgressChanged(view, newProgress)

        }
    }

    private class WebViewClientClass : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            Log.d(ERROR, "$request")

            return super.shouldOverrideUrlLoading(view, request)

        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {

            Log.d(ERROR, "${error?.primaryError}")
            handler?.proceed()

        }
    }
}