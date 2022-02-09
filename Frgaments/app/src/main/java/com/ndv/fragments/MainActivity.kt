package com.ndv.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), PersonsListFragmentListener,
    PersonDetailsFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment =
            supportFragmentManager.findFragmentById(R.id.personListsFragment) as PersonsListFragment
        fragment.personsToDisplay = persons
    }


    //PersonsListFragmentListener
    override fun onClickPerson(position: Int) {
        val person = persons[position]
        val fragmentContainer = findViewById<View>(R.id.personDetailsFragment_container)

        if (fragmentContainer != null) {
            onClickPersonWithFragment(person)
        } else {
            onClickPersonWithoutFragment(person)
        }
    }

    //PersonDetailsFragmentListener
    override fun getSelectedPerson(name: String): Person {
        val error = getString(R.string.error_no_person)
        return persons.find { it.name == name } ?: throw Error(error)
    }

    //Privates
    private fun onClickPersonWithoutFragment(person: Person) {
        val intent = Intent(baseContext, PersonDetailActivity::class.java)

        intent.putExtra(PersonDetailActivity.PERSON_NAME, person.name)
        startActivity(intent)
    }

    private fun onClickPersonWithFragment(person: Person) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = PersonDetailsFragment()
        fragment.personName = person.name


        transaction.replace(R.id.personDetailsFragment_container, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}