package com.ndv.sqlite

import android.app.Application
import com.ndv.sqlite.personDatabase.PersonRepository
import com.ndv.sqlite.personDatabase.PersonRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PersonsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { PersonRoomDatabase.getDatabase(this) }
    val repository by lazy { PersonRepository(database.personDao()) }
}
