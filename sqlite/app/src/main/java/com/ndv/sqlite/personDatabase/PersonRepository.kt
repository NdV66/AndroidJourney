package com.ndv.sqlite.personDatabase

import androidx.annotation.WorkerThread
import com.ndv.sqlite.Person
import com.ndv.sqlite.PersonDao
import kotlinx.coroutines.flow.Flow

class PersonRepository(private val personDao: PersonDao) {
    val allPersons: Flow<List<Person>> = personDao.getAlphabetizedPersons()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.insert(person)
    }
}
