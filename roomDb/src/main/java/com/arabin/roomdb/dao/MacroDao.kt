package com.arabin.roomdb.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arabin.roomdb.entity.Macronym

@Dao
interface MacroDao {

    @Query("SELECT * FROM macros")
    suspend fun getAll(): List<Macronym>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(macros: List<Macronym>)

    @Delete
    suspend fun delete(macro: Macronym)

}