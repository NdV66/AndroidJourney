package com.ndv.fragments

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PersonDetailActivity : AppCompatActivity() {
    companion object {
        const val PERSON_NAME = "personName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)

        val personName = intent.getStringExtra(PERSON_NAME)
        val fragment: PersonDetailsFragment =
            supportFragmentManager.findFragmentById(R.id.personDetailsFragment) as PersonDetailsFragment

        if (personName != null) {
            fragment.personName = personName
        }
    }
}