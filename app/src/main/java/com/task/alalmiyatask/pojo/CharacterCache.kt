package com.task.alalmiyatask.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "site_word")
data class CharacterCache(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var characters_site: String): Serializable
