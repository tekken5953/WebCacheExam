package com.example.webcacheexam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onDestroy() {
        super.onDestroy()
        webView.clearCache(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView= findViewById(R.id.webView)
        val webSetting: WebSettings = webView.settings
        val back: ImageView = findViewById(R.id.mainBack)
        val refresh: ImageView = findViewById(R.id.mainRefresh)

        webSetting.apply {
            this.loadWithOverviewMode = true
            this.builtInZoomControls = true
            javaScriptEnabled = true
            this.setSupportZoom(true) // 핀치 줌 허용
            this.useWideViewPort = true // 화면 맞추기
            this.domStorageEnabled = true // 로컬 저장소 허용
            this.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK // 브라우저 캐시 허용

            webView.webChromeClient = WebChromeClient()
            webView.webViewClient = WebViewClient()

        }

        webView.loadUrl("https://tekken5953.tistory.com/")


        back.setOnClickListener {
            if (webView.canGoBack())
                webView.goBack()
        }

        refresh.setOnClickListener {
            webView.reload()
        }
    }
}