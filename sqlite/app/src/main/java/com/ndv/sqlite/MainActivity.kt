package com.ndv.sqlite

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ndv.sqlite.personDatabase.Person
import com.ndv.sqlite.personDatabase.PersonViewModel
import com.ndv.sqlite.personDatabase.PersonViewModelFactory


class MainActivity : AppCompatActivity() {
    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personViewModel.allPersons.observe(this) {
            setupSpinner(it)
        }

        setupEditButton()
        setupAddButton()
    }

    private fun setupSpinner(persons: List<Person>) {
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter =
            ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, persons.map { it.name })
    }

    private fun setupEditButton() {
        val button = findViewById<Button>(R.id.editButton)

        button.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(PERSON_ID, 11)
            startActivity(intent)
        }
    }

    private fun setupAddButton() {
        val button = findViewById<Button>(R.id.addButton)

        button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
