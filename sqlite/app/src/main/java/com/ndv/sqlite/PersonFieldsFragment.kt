package com.ndv.sqlite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.ndv.sqlite.personDatabase.Person

//https://kotlinlang.org/docs/fun-interfaces.html
fun interface IPersonFieldsFragmentListener {
    fun onClickSave(person: Person)
}

class PersonFieldsFragment : Fragment() {
    lateinit var listener: IPersonFieldsFragmentListener
    var person: Person? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_fields, container, false)
    }

    override fun onStart() {
        super.onStart()

        setupSaveButton()
        person?.let {
            setupInitial(it)
        }
    }

    private fun setupInitial(data: Person) {
        val view = requireView()
        val nameText = view.findViewById<EditText>(R.id.nameText)
        val descriptionText = view.findViewById<EditText>(R.id.descriptionText)

        nameText.setText(data.name)
        descriptionText.setText(data.description)
    }

    private fun setupSaveButton() {
        val view = requireView()
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val nameText = view.findViewById<EditText>(R.id.nameText)
        val descriptionText = view.findViewById<EditText>(R.id.descriptionText)


        saveButton.setOnClickListener {
            val name = nameText.text.toString()
            val description = descriptionText.text.toString()


            if (person == null) {
                listener.onClickSave(Person(name, description))
            } else {
                listener.onClickSave(Person(name, description, person!!.id))
            }
        }
    }
}