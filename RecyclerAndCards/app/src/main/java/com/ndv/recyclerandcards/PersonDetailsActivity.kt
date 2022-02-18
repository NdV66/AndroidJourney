package com.ndv.recyclerandcards

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

const val PERSON_NAME = "PERSON_NAME"

class PersonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        val personName = intent.extras!!.getString(PERSON_NAME)!!
        val person = getPersonByName(personName, VALAR)!!

        setupImage(person.imageId)
        setupName(person.name)
    }

    private fun setupImage(imageId: Int) {
        val imageView = findViewById<ImageView>(R.id.imageDetail)
        val drawableImage = ContextCompat.getDrawable(this, imageId)
        imageView.setImageDrawable(drawableImage)
    }

    private fun setupName(name: String) {
        val textView = findViewById<TextView>(R.id.nameDetail)
        textView.text = name
    }
}