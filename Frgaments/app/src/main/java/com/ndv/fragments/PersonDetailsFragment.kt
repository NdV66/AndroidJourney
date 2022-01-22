package com.ndv.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class PersonDetailsFragment : Fragment() {
    var personName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onStart() {
        super.onStart()

        val person = getSelectedPerson(personName)
        fillPersonDetails(person)
    }

    private fun getSelectedPerson(name: String): Person =
        getPersonByName(name) ?: throw Error(getString(R.string.error_no_person))

    private fun fillPersonDetails(person: Person) {
        val nameTextView = view?.findViewById<TextView>(R.id.personName)
        val raceTextView = view?.findViewById<TextView>(R.id.personRace)
        val detailsTextView = view?.findViewById<TextView>(R.id.personDetails)

        nameTextView?.text = person.name
        raceTextView?.text = person.race.printableName
        detailsTextView?.text = person.description

    }
}