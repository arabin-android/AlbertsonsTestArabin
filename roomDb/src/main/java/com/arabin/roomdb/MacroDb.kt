package com.arabin.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arabin.roomdb.dao.MacroDao
import com.arabin.roomdb.entity.Macronym

@Database(entities = [Macronym::class], version = 1)
abstract class MacroDb : RoomDatabase(){
    abstract fun macroDao(): MacroDao
}