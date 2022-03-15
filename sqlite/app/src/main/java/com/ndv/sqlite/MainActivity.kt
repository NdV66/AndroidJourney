package com.ndv.sqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ndv.sqlite.personDatabase.Person
import com.ndv.sqlite.personDatabase.PersonViewModel
import com.ndv.sqlite.personDatabase.PersonViewModelFactory
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personViewModel.allPersons.observe(this) {
            setSpinnerAdapter(it)
        }

        setupEditButton()
        setupAddButton()
        setupSpinner()
    }

    private fun setSpinnerAdapter(persons: List<Person>) {
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter =
            ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, persons)
    }


    private fun setupSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val description = findViewById<TextView>(R.id.text)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long,
            ) {
                runBlocking {
                    val personName = spinner.selectedItem.toString()
                    val person = personViewModel.getPersonByName(personName)
                    description.text = person.description
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    private fun setupEditButton() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val button = findViewById<Button>(R.id.editButton)


        button.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(PERSON_NAME, spinner.selectedItem.toString())
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
