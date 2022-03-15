package com.ndv.sqlite.personDatabase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

const val PERSON_TABLE = "person_table"
const val NAME_COL = "name"
const val DESCRIPTION_COL = "description"

@Entity(tableName = PERSON_TABLE)
class Person(
    @ColumnInfo(name = NAME_COL) val name: String,
    @ColumnInfo(name = DESCRIPTION_COL) val description: String,
    @PrimaryKey(autoGenerate = true) val _id: Long= 0,
)

@Dao
interface PersonDao {
    @Query("SELECT * FROM $PERSON_TABLE ORDER BY $NAME_COL ASC")
    fun getAlphabetizedPersons(): Flow<List<Person>>

    @Query("SELECT * FROM $PERSON_TABLE WHERE NAME = :name")
    fun getPersonByName(name: String): Flow<Person>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM $PERSON_TABLE")
    suspend fun deleteAll()

    @Insert
    fun insertAll(vararg persons: Person)
}
