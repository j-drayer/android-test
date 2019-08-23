package com.example.imalearnsomethin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

const val EXTRA_MESSAGE = "some_message_I_guess"

class MainActivity : AppCompatActivity() {
    private val validationFields = mutableListOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validationFields += findViewById<TextView>(R.id.firstNameValidation)
        validationFields += findViewById<TextView>(R.id.lastNameValidation)
    }

    fun sendRequest(view: View){
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.example.com"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val intent = Intent(this, ResponseActivity::class.java).apply {
                    putExtra("response", response.toString())
                }
                startActivity(intent)
            },
            Response.ErrorListener {
                println(it.message)
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun sendMessage(view: View) {
        validationFields.forEach{
            it.visibility = View.INVISIBLE
        }
        when {
            firstNameIsMissing() -> findViewById<TextView>(R.id.firstNameValidation).visibility = View.VISIBLE
            lastNameIsMissing() -> findViewById<TextView>(R.id.lastNameValidation).visibility = View.VISIBLE
            else -> {
                val firstName = findViewById<EditText>(R.id.firstName).text.toString()
                val lastName = findViewById<EditText>(R.id.lastName).text.toString()
                val message = "$firstName $lastName"
                val intent = Intent(this, DisplayMessageActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)
            }
        }
    }

    private fun lastNameIsMissing(): Boolean {
        return findViewById<EditText>(R.id.lastName).text.isEmpty()
    }

    private fun firstNameIsMissing(): Boolean {
        return findViewById<EditText>(R.id.firstName).text.isEmpty()
    }
}
