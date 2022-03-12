package com.ndv.sqlite

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSpinner()
        setupEditButton()
        setupAddButton()
    }

    private fun setText(text: String) {
        val textView = findViewById<TextView>(R.id.text)
        textView.text = text
    }

    private fun getDescriptionByName(name: String): String {
        return "DUPA"
    }

    private fun onSelectName(cursor: Cursor) {
        val selectedName = cursor.getString(1)
        val description = getDescriptionByName(selectedName)
        setText(description)
    }

    private fun setupSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner)
//        spinner.adapter =


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long,
            ) {
                //
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupEditButton() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val button = findViewById<Button>(R.id.editButton)

        button.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(PERSON_ID, 11)
            startActivity(intent)
        }
    }

    private fun setupAddButton() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        val button = findViewById<Button>(R.id.addButton)

        button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
