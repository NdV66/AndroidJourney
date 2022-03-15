package com.ndv.sqlite

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEditButton()
        setupAddButton()
    }


    private fun setupEditButton() {
        val button = findViewById<Button>(R.id.editButton)

        button.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(PERSON_ID, 11)
            startActivity(intent)
        }
    }

    private fun setupAddButton() {
        val button = findViewById<Button>(R.id.addButton)

        button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
