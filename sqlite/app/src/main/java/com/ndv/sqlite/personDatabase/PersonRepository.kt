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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getPersonByName(name: String): Person {
       return personDao.getPersonByName(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updatePerson(person: Person) {
         personDao.updatePersons(person)
    }
}
