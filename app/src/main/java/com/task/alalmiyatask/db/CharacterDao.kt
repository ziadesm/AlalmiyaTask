package com.task.alalmiyatask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.alalmiyatask.pojo.CharacterCache

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWord(characterCache: CharacterCache): Long

    @Query("SELECT * FROM site_word")
    suspend fun getSiteWord(): CharacterCache
}