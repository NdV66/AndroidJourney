package com.ndv.sqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

const val PERSON_ID = "PERSON_ID"

class EditActivity : AppCompatActivity() {
    private lateinit var personId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        personId = intent.getStringExtra(PERSON_ID)!!

        if (savedInstanceState == null) {
            setupFragment()
        }
    }

    private fun prepareListener(): IPersonFieldsFragmentListener {
        return IPersonFieldsFragmentListener { name: String, description: String ->
            println(">> kliklem $name $description")
            onBackPressed()
        }
    }

    private fun setupFragment() {
        val fragment = PersonFieldsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        fragment.listener = prepareListener()
//        fragment.person = xxx

        transaction.add(R.id.editPersonFragment, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}