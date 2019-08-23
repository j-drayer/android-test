package com.example.imalearnsomethin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResponseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        val responseValue = intent.getStringExtra("response")
        findViewById<TextView>(R.id.response).apply {
            text = responseValue
        }
    }
}
