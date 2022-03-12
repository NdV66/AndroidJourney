package com.ndv.sqlite.personDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ndv.sqlite.Person
import com.ndv.sqlite.PersonDao

private const val VERSION = 1
const val DATABASE_NAME = "person_database"

@Database(entities = [Person::class], version = VERSION, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(context: Context): PersonRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}
