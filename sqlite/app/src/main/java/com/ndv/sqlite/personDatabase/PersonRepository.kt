package com.ndv.sqlite.personDatabase

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PersonRepository(private val personDao: PersonDao) {
    val allPersons: Flow<List<Person>> = personDao.getAlphabetizedPersons()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.insert(person)
    }

    @WorkerThread
    suspend fun getPersonByName(name: String) {
        personDao.getPersonByName(name)
    }
}
