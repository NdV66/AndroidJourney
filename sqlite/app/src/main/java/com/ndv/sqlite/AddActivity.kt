package com.ndv.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.ndv.sqlite.personDatabase.Person
import com.ndv.sqlite.personDatabase.PersonViewModel
import com.ndv.sqlite.personDatabase.PersonViewModelFactory

class AddActivity : AppCompatActivity() {
    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setupFragment()
    }

    private fun prepareListener(): IPersonFieldsFragmentListener {
        return IPersonFieldsFragmentListener { name: String, description: String ->
            personViewModel.insert(Person(name, description))
            onBackPressed()
        }
    }

    private fun setupFragment() {
        val personFieldsFragment = PersonFieldsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        personFieldsFragment.listener = prepareListener()

        transaction.replace(R.id.addPersonFragmentContainer, personFieldsFragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}