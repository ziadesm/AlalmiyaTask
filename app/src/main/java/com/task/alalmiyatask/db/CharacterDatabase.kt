package com.task.alalmiyatask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.alalmiyatask.pojo.CharacterCache

@Database(entities = [CharacterCache::class], version = 1, exportSchema = false)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}