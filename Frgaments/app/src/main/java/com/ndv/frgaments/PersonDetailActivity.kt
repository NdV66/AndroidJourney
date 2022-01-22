package com.ndv.frgaments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.Error

class PersonDetailActivity : AppCompatActivity() {
    companion object {
        const val PERSON_NAME = "personName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)

        val person = getSelectedPerson()
        fillPersonDetails(person)
    }

    private fun getSelectedPerson(): Person {
        val personName =
            intent.getStringExtra(PERSON_NAME) ?: throw Error(getString(R.string.error_empty_name))
        return getPersonByName(personName) ?: throw Error(getString(R.string.error_no_person))
    }

    private fun fillPersonDetails(person: Person) {
        val nameTextView = findViewById<TextView>(R.id.personName)
        val raceTextView = findViewById<TextView>(R.id.personRace)
        val detailsTextView = findViewById<TextView>(R.id.personDetails)

        nameTextView.text = person.name
        raceTextView.text = person.race.printableName
        detailsTextView.text = person.description
    }
}