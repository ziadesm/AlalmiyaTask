package com.task.alalmiyatask.utils
import com.task.alalmiyatask.pojo.CharacterModel

object CharacterValidation {
    fun getCharacterValidationList(str: String): List<CharacterModel> {
        val list: MutableList<CharacterModel> = mutableListOf()

        val regex = Regex("[\\n\\r\\t]")
        val str1 = regex.replace(str, " ")
        val words = str1.split(" ")

        val wordMap: MutableMap<String, Int?> = HashMap()

        words.map { word ->
            if (wordMap[word] != null) wordMap[word] = wordMap[word]!! + 1
            else wordMap[word] = 1
        }

        val wordSet: Set<String> = wordMap.keys
        wordSet.map { word ->
            if (word != "" && wordMap[word]!! >= 1) list.add(CharacterModel(word, wordMap[word]!!))
        }

        return list
    }
}