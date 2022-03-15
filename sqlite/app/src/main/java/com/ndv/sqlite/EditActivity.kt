package com.ndv.sqlite

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.ndv.sqlite.personDatabase.Person
import com.ndv.sqlite.personDatabase.PersonViewModel
import com.ndv.sqlite.personDatabase.PersonViewModelFactory
import kotlinx.coroutines.runBlocking

const val PERSON_NAME = "PERSON_NAME"

class EditActivity : AppCompatActivity() {
    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val personName = intent.getStringExtra(PERSON_NAME)!!

        runBlocking {
            val person = personViewModel.getPersonByName(personName)
            setupFragment(person)
        }

    }

    private fun prepareListener(): IPersonFieldsFragmentListener {
        return IPersonFieldsFragmentListener { name: String, description: String ->
            personViewModel.updatePerson(Person(name, description))
            onBackPressed()
        }
    }

    private fun setupFragment(person: Person) {
        val fragment = PersonFieldsFragment()
        val transaction = supportFragmentManager.beginTransaction()

        fragment.listener = prepareListener()
        fragment.person = person

        transaction.add(R.id.editPersonFragment, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}