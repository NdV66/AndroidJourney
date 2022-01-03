package com.ndv.layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showHorizontalButton = findViewById<Button>(R.id.showLinearHorizontal)
        val showLinearButton = findViewById<Button>(R.id.showLinearHorizontal)

        showHorizontalButton.setOnClickListener {
            val intent = Intent(this, LinearHorizontal::class.java)
            startActivity(intent)
        }
    }
}