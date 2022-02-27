package com.ndv.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupReadButton()
    }

    private fun setText(text: String) {
        val textView = findViewById<TextView>(R.id.text)
        textView.text = text
    }

    private fun setupReadButton() {
        val button = findViewById<Button>(R.id.readButton)
        button.setOnClickListener { setText("TEST") }
    }
}
