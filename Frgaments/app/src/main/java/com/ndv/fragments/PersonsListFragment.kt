package com.ndv.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment


class PersonsListFragment : Fragment() {
//    val personsToDisplay: Array<Person> = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_persons_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        setupPersonsList()
        setupOnClickPersonListener()
    }

    private fun setupPersonsList() {
        val personsNames = persons.map { it.name }
        val adapter =
            ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                personsNames
            )

        val list = view?.findViewById<ListView>(R.id.personsList)
        list?.adapter = adapter
    }

    private fun setupOnClickPersonListener() {
        val list = view?.findViewById<ListView>(R.id.personsList)

        list?.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(requireContext(), PersonDetailActivity::class.java)
            val person = persons[position]

            intent.putExtra(PersonDetailActivity.PERSON_NAME, person.name)
            startActivity(intent)
        }
    }


}
