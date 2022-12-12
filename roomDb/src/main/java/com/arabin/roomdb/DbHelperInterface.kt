package com.arabin.roomdb

import com.arabin.roomdb.entity.Macronym

interface DbHelperInterface {
    suspend fun getAllMacros(): List<Macronym>
    suspend fun insertAllMacros(items: List<Macronym>)
}