package com.ndv.sharedviewmodel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStartButton()
    }


    private fun setupStartButton() {
        val button = findViewById<Button>(R.id.startButton)
        button.setOnClickListener {
            val intent = Intent(applicationContext, StepperActivity::class.java)
            startActivity(intent)
        }
    }
}