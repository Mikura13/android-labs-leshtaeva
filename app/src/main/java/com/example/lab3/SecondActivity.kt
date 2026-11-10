package com.example.lab3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val buttonPrev = findViewById<Button>(R.id.buttonPrev)

        val receivedText = intent.getStringExtra("text2remember")
        textViewResult.text = receivedText ?: ""

        buttonPrev.setOnClickListener {
            finish()
        }
    }
}