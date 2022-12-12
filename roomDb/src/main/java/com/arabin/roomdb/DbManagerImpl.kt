package com.arabin.roomdb

import android.content.Context

open class DbManagerImpl: DbManagerInterface {

    private var macroDb: MacroDb? = null

    override fun getRoomDbRepo(context: Context): DbManagerRepo? {
        if (macroDb == null)
            macroDb = DbBuilder.getDbInstance(context)
        return macroDb?.let { DbManagerRepo(it) }
    }
}