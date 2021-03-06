package com.camo.template.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.camo.template.database.local.dao.*
import com.camo.template.database.local.model.Coin


// Increase version every time you make changes to room database structure
@Database(entities = [Coin::class], version = 1)
//@TypeConverters(DateTypeConverter::class)
abstract class LocalAppDb : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}