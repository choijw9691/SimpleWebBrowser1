package com.example.simplewebbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private val goHomeButton: ImageButton by lazy {
        findViewById(R.id.goHomeButton)
    }
    private val addressBar: EditText by lazy {
        findViewById(R.id.addressBar)
    }
    private val goBackButton: ImageButton by lazy {
        findViewById(R.id.goBackButton)
    }
    private val goForwardButton: ImageButton by lazy {
        findViewById(R.id.goForwardButton)
    }


    private val webView: WebView by lazy {
        findViewById(R.id.webView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()//종료 백버튼 실행
            // TODO: 2021-08-09 종료 직전 확인 메세지 보여줘야 됨  
        }
    }

    private fun initViews() {
        webView.apply {
            webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(DEFAULT_URL)
        }//apply : webview를 3번 호출하던걸 한번으로 줄여줌
    }

    private fun bindViews() {
        addressBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                webView.loadUrl(v.text.toString())
            }
            return@setOnEditorActionListener false

        }
        goBackButton.setOnClickListener {
            webView.goBack()
        }
        goForwardButton.setOnClickListener {
            webView.goForward()
        }
        goHomeButton.setOnClickListener {
            webView.loadUrl(DEFAULT_URL)
            // TODO: 2021-08-09 홈버튼 눌렀을때 히스토리 없어지도록 수정 해야됨 
        }

    }

    companion object {
        private const val DEFAULT_URL = "http://www.google.com"
    }
}