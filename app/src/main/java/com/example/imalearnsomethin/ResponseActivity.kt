package com.example.imalearnsomethin

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class ResponseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        val responseValue = intent.getStringExtra("response")
        findViewById<WebView>(R.id.response).apply {
            this.loadData(responseValue, "text/html","UTF-8")
        }
    }
}
