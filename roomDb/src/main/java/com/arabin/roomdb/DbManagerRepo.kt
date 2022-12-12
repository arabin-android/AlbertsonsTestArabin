package com.arabin.roomdb

import com.arabin.roomdb.entity.Macronym

class DbManagerRepo(private val macroDb: MacroDb) : DbHelperInterface{
    override suspend fun getAllMacros(): List<Macronym> {
        return macroDb.macroDao().getAll()
    }

    override suspend fun insertAllMacros(items: List<Macronym>) {
        macroDb.macroDao().insertAll(items)
    }


}