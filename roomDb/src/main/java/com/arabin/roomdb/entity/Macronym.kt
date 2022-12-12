package com.arabin.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "macros")
data class Macronym (
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "shortName")val shortName: String,
    @ColumnInfo(name = "details")val details: String
)