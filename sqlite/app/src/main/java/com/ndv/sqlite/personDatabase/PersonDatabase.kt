package com.ndv.sqlite.personDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ndv.sqlite.Person
import com.ndv.sqlite.PersonDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val VERSION = 1
const val DATABASE_NAME = "person_database"

@Database(entities = [Person::class], version = VERSION, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    private class PersonDatabaseCallback(
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val personDao = database.personDao()
                    personDao.deleteAll()

                    val person = Person(name = "Irmo", description = "Lord of the Lorien Garden")
                    personDao.insert(person)

                }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): PersonRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(PersonDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}
