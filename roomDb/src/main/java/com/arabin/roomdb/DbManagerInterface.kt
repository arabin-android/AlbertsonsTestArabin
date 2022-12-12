package com.arabin.roomdb

import android.content.Context

interface DbManagerInterface {

    fun getRoomDbRepo(context: Context): DbManagerRepo?

}