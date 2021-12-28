package com.ndv.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callButton = findViewById<Button>(R.id.callButton)
        val randomTargetButton = findViewById<Button>(R.id.randomTargetButton)
        val requestEditText = findViewById<EditText>(R.id.targetRequest)

        randomTargetButton.setOnClickListener {
            requestEditText.text = getRandomTarget()
        }

        callButton.setOnClickListener {
            val intent = Intent(this, PalantirResponse::class.java)
            val text = requestEditText.text.toString()
            intent.putExtra(PalantirResponse.REQUEST_TARGET, text)
            startActivity(intent)
        }
    }
}

