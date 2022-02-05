package com.ndv.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

interface PersonDetailsFragmentListener {
    fun getSelectedPerson(name: String): Person
}

const val PERSON_NAME_KEY = "personName"

class PersonDetailsFragment : Fragment() {
    var personName = ""
    private lateinit var listener: PersonDetailsFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as PersonDetailsFragmentListener
    }

    override fun onStart() {
        super.onStart()

        try {
            val person = listener.getSelectedPerson(personName)
            fillPersonDetails(person)
        } catch (err: Error) {
            println(err)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null) {
            personName = savedInstanceState.getString(PERSON_NAME_KEY) ?: ""
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(PERSON_NAME_KEY, personName)
    }

    //Privates
    private fun fillPersonDetails(person: Person) {
        val nameTextView = view?.findViewById<TextView>(R.id.personName)
        val raceTextView = view?.findViewById<TextView>(R.id.personRace)
        val detailsTextView = view?.findViewById<TextView>(R.id.personDetails)

        nameTextView?.text = person.name
        raceTextView?.text = person.race.printableName
        detailsTextView?.text = person.description
    }
}