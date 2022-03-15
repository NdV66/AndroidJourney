package com.ndv.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "personsList"
const val DATABASE_VERSION = 1

const val TABLE_NAME = "PERSON"
const val NAME_COL = "NAME"
const val DESCRIPTION_COL = "DESCRIPTION"

val INIT_PERSONS = arrayOf(
    Person("Irmo", "One of the Valar. He lives in Lorien."),
    Person("Namo", "One of the Valar. He lives in Mandos.")
)

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        fun updatePersonById(db: SQLiteDatabase, id: String, name: String, description: String) {
            val values = ContentValues()
            values.put(DESCRIPTION_COL, description)
            values.put(NAME_COL, name)

            db.update(TABLE_NAME,
                values,
                "_id = ?",
                arrayOf(id))
        }

        fun removePersonById(db: SQLiteDatabase, id: Int,) {
            db.delete(TABLE_NAME,
                "_id = ?",
                arrayOf(id.toString()))
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createDatabase(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 1) {
            createDatabase(db)
        }
    }

    private fun insertOnePerson(db: SQLiteDatabase, person: Person) {
        val personValues = ContentValues()
        personValues.put(NAME_COL, person.name)
        personValues.put(DESCRIPTION_COL, person.description)
        db.insert(TABLE_NAME, null, personValues)
    }

    private fun createDatabase(db: SQLiteDatabase?) {
        if (db != null) {
            val createTableSql =
                "CREATE TABLE PERSON (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "NAME TEXT, " +
                        "DESCRIPTION TEXT)"
            db.execSQL(createTableSql)
            INIT_PERSONS.forEach { insertOnePerson(db, it) }
        }
    }
}
