package com.ndv.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

interface PersonsListFragmentListener {
    fun onClickPerson(position: Int)
}

class PersonsListFragment : Fragment() {
    lateinit var personsToDisplay: Array<Person>
    lateinit var listener: PersonsListFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_persons_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as PersonsListFragmentListener
    }

    override fun onStart() {
        super.onStart()

        setupPersonsList()
        setupOnClickPersonListener()
    }

    private fun setupPersonsList() {
        val personsNames = personsToDisplay.map { it.name }
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
            listener.onClickPerson(position)
        }
    }


}
