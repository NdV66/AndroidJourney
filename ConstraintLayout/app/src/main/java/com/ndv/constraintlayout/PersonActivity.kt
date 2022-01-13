package com.ndv.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PersonActivity : AppCompatActivity() {
    companion object {
        const val PERSON_ID = "personId"
        const val RACE = "race"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        val personId = intent.extras!!.get(PERSON_ID) as Int
        val race = intent.extras!!.get(RACE)
        val persons = personsByRace[race]!!

        val nameTextView = findViewById<TextView>(R.id.personName)
        nameTextView.text = persons[personId].name
    }
}