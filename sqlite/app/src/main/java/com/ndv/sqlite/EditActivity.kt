package com.ndv.sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader

const val PERSON_ID = "PERSON_ID"

class EditActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    private lateinit var personId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        try {
            personId = intent.getStringExtra(PERSON_ID)!!
            db = DatabaseHelper(this)
            setupInitial(personId)
            setupSaveButton()
        } catch (e: SQLiteException) {
            handleError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

    private fun handleError() {
        val toast = Toast.makeText(this, getString(R.string.databaseError), Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun preparePersonCursor(id: String): Cursor {
        return db.readableDatabase.query(TABLE_NAME,
            arrayOf("_id", NAME_COL, DESCRIPTION_COL),
            "_id = ?",
            arrayOf(id),
            null,
            null,
            null)
    }

    private fun setupInitial(personId: String) {
        val personCursor = preparePersonCursor(personId)
        val nameText = findViewById<EditText>(R.id.nameText)
        val descriptionText = findViewById<EditText>(R.id.descriptionText)

        if (personCursor.moveToFirst()) {
            nameText.setText(personCursor.getString(1))
            descriptionText.setText(personCursor.getString(2))
        } else {
            handleError()
        }

        personCursor.close()
    }

    private fun setupSaveButton() {
        val saveButton = findViewById<Button>(R.id.saveButton)
        val nameText = findViewById<EditText>(R.id.nameText)
        val descriptionText = findViewById<EditText>(R.id.descriptionText)

        saveButton.setOnClickListener {
            DatabaseHelper.updatePersonById(db.writableDatabase,
                personId,
                nameText.text.toString(),
                descriptionText.text.toString()
            )

            onBackPressed()
        }
    }


}