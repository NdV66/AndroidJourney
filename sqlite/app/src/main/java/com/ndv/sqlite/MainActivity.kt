package com.ndv.sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var namesCursor: Cursor
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            db = DatabaseHelper(this)
            namesCursor = prepareNamesCursor()
            setupSpinner()
        } catch (e: SQLiteException) {
            handleError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        namesCursor.close()
        db.close()
    }


    private fun setText(text: String) {
        val textView = findViewById<TextView>(R.id.text)
        textView.text = text
    }

    private fun handleError() {
        val toast = Toast.makeText(this, getString(R.string.databaseError), Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun prepareNamesCursor(): Cursor {
        return db.readableDatabase.query(TABLE_NAME,
            arrayOf("_id", NAME_COL),
            null,
            null,
            null,
            null,
            "$NAME_COL ASC")
    }

    private fun prepareDescriptionCursor(name: String): Cursor {
        return db.readableDatabase.query(TABLE_NAME,
            arrayOf(DESCRIPTION_COL),
            "$NAME_COL = ?",
            arrayOf(name),
            null,
            null,
            "$NAME_COL ASC")
    }

    private fun getDescriptionByName(name: String): String {
        val cursor = prepareDescriptionCursor(name)
        var description = ""

        if (cursor.moveToFirst()) {
            description = cursor.getString(0)
        } else {
            handleError()
        }

        cursor.close()
        return description
    }


    private fun setupSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter =
            SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                namesCursor,
                arrayOf(NAME_COL),
                intArrayOf(android.R.id.text1),
                0
            )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long,
            ) {
                println(id)
                val cursor = spinner.selectedItem as Cursor
                val selectedName = cursor.getString(1)
                val description = getDescriptionByName(selectedName)
                setText(description)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}
