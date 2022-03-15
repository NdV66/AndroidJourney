package com.ndv.sqlite.personDatabase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

const val PERSON_TABLE = "person_table"
const val NAME_COL = "name"
const val DESCRIPTION_COL = "description"
const val ID_COL = "id"

@Entity(tableName = PERSON_TABLE)
class Person(
    @ColumnInfo(name = NAME_COL) val name: String,
    @ColumnInfo(name = DESCRIPTION_COL) val description: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COL)
    val id: Long = 0,
) {
    override fun toString(): String {
        return name
    }
}

@Dao
interface PersonDao {
    @Query("SELECT * FROM $PERSON_TABLE ORDER BY $NAME_COL ASC")
    fun getAlphabetizedPersons(): Flow<List<Person>>

    @Query("SELECT * FROM $PERSON_TABLE WHERE NAME = :name")
    suspend fun getPersonByName(name: String): Person

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM $PERSON_TABLE")
    suspend fun deleteAll()

    @Delete
    suspend fun deletePersons(vararg persons: Person)

    @Insert
    fun insertAll(vararg persons: Person)

    @Update
    suspend fun updatePersons(vararg persons: Person)

}
