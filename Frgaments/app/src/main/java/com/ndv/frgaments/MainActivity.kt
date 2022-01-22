package com.ndv.frgaments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPersonsList()
        setupOnClickPersonListener()
    }

    private fun setupPersonsList() {
        val personsNames = persons.map { it.name }
        val adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, personsNames)
        val list = findViewById<ListView>(R.id.personsList)
        list.adapter = adapter
    }

    private fun setupOnClickPersonListener() {
        val list = findViewById<ListView>(R.id.personsList)
        list.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PersonDetailActivity::class.java)
            val person = persons[position]

            intent.putExtra(PersonDetailActivity.PERSON_NAME, person.name)
            startActivity(intent)
        }
    }
}