package com.ndv.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Error

class MainActivity : AppCompatActivity(), PersonsListFragmentListener, PersonDetailsFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment =
            supportFragmentManager.findFragmentById(R.id.personListsFragment) as PersonsListFragment
        fragment.personsToDisplay = persons
    }

    override fun onClickPerson(position: Int) {
        val intent = Intent(baseContext, PersonDetailActivity::class.java)
        val person = persons[position]

        intent.putExtra(PersonDetailActivity.PERSON_NAME, person.name)
        startActivity(intent)
    }

    override fun getSelectedPerson(name: String): Person {
        val error = getString(R.string.error_no_person)
        return persons.find { it.name == name } ?: throw Error(error)
    }
}