package com.ndv.sqlite.personDatabase

import androidx.lifecycle.*
import kotlinx.coroutines.launch


class PersonViewModel(private val repository: PersonRepository) : ViewModel() {
    val allPersons: LiveData<List<Person>> = repository.allPersons.asLiveData()

    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun updatePerson(person: Person) = viewModelScope.launch {
        repository.updatePerson(person)
    }

    fun deletePerson(person: Person) = viewModelScope.launch {
        repository.deletePerson(person)
    }

    suspend fun getPersonByName(name: String): Person {
        return repository.getPersonByName(name)
    }

}

class PersonViewModelFactory(private val repository: PersonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
