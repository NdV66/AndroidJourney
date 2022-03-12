package com.ndv.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setupFragment()
    }

    private fun prepareListener(): IPersonFieldsFragmentListener {
        return IPersonFieldsFragmentListener { name: String, description: String ->
            println(">> kliklem add $name $description")
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