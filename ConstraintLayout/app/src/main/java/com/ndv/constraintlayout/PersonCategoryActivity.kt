package com.ndv.constraintlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class PersonCategoryActivity : AppCompatActivity() {
    companion object {
        const val RACE = "race"
    }

    private fun setAdapter(persons: Array<Person>) {
        val list = findViewById<ListView>(R.id.persons)
        val adapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, persons)
        list.adapter = adapter
    }

    private fun setupOnClickElement() {
        val list = findViewById<ListView>(R.id.persons)
        list.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, PersonActivity::class.java)
            val race = races[position]

            intent.putExtra(PersonActivity.PERSON_ID, position)
            intent.putExtra(PersonActivity.RACE, race)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_category)

        val race = intent.extras!!.get(RACE) as Races

        val categoryNameTextView = findViewById<TextView>(R.id.personCategoryName)
        categoryNameTextView.text = race.value

        val persons = personsByRace[race]
        if (persons != null) {
            setAdapter(persons)
            setupOnClickElement()
        }
    }
}

