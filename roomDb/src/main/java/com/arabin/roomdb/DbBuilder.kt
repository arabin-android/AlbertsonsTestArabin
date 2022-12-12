package com.arabin.roomdb

import android.content.Context
import androidx.room.Room

object DbBuilder {

    private var INSTANCE: MacroDb? = null

    fun getDbInstance(context: Context): MacroDb?{
        if (INSTANCE == null){
            synchronized(MacroDb::class){
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(context.applicationContext,
        MacroDb::class.java, "macro_db").build()

}