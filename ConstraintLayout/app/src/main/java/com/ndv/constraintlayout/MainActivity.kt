package com.ndv.constraintlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, races)
        val racesList = findViewById<ListView>(R.id.races)

        racesList.adapter = adapter

        racesList.setOnItemClickListener { _, _, position, _ ->
            val race = races[position]
            val intent = Intent(this, PersonCategoryActivity::class.java)
            intent.putExtra(PersonCategoryActivity.RACE, race)
            startActivity(intent)
        }
    }
}