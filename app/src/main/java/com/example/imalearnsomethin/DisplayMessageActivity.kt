package com.example.imalearnsomethin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_mesage)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.textView).apply {
            text = message
        }
    }
}
