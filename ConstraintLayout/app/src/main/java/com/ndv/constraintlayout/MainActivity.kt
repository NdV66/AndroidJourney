package com.ndv.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val racesList = findViewById<ListView>(R.id.races)

        racesList.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->


        }
    }
}