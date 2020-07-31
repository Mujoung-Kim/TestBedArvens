package com.todaysquare.patterntasknote.ui.notice_webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.utils.Constants.Param.Companion.EXTRA_NOTICE_LINK
import kotlinx.android.synthetic.main.activity_notice_web_view.*

class NoticeWebViewActivity : AppCompatActivity() {
    private lateinit var mWebSettings: WebSettings
    private var url: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_web_view)

        processIntent()
        loadWebView()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView() {
        wv_webView.webViewClient = WebViewClient()                  //  클릭 시 새 창 생성안함.
        mWebSettings = wv_webView.settings                          //  세부 세팅 등록

        mWebSettings.javaScriptEnabled = true                       //  자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(false)               //  새 창 생성 허용 여부
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false  //  자바스크립트 새 창 허용 여부
        mWebSettings.loadWithOverviewMode = true                    //  메타 태그 허용 여부
        mWebSettings.useWideViewPort = true                         //  화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false)                          //  화면 줌 허용 여부
        mWebSettings.builtInZoomControls = false                    //  화면 확대 및 축소 허용 여부
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE          //  브라우저 캐시 허용 여부
        mWebSettings.domStorageEnabled = true                       //  로컬 저장소 허용 여부
        wv_webView.loadUrl(url)                                     //  시작 주소 or 표시할 주소

    }

    private fun processIntent() {
        url = intent.getStringExtra(EXTRA_NOTICE_LINK)

    }
}