package com.ndv.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Error

class PersonDetailActivity : AppCompatActivity(), PersonDetailsFragmentListener {
    companion object {
        const val PERSON_NAME = "personName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)

        val personName = intent.getStringExtra(PERSON_NAME)
        val fragment =
            supportFragmentManager.findFragmentById(R.id.personDetailsFragment) as PersonDetailsFragment

        if (personName != null) {
            fragment.personName = personName
        }
    }

    override fun getSelectedPerson(name: String): Person {
        val error = getString(R.string.error_no_person)
        return persons.find { it.name == name } ?: throw Error(error)
    }
}