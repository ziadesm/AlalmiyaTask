package com.task.alalmiyatask.utils

import java.lang.Exception
/**
* @author Zeyad Alsayed Mohammed
* @property This class use other data class and object to determine which
 *          State Should be handled, in main view
* */
sealed class CharacterDataState<out R>{
    data class Success<out T>(val data: T): CharacterDataState<T>()
    data class Error(val e: Exception): CharacterDataState<Nothing>()
    data class Cancel(val e: String): CharacterDataState<Nothing>()
    object Loading: CharacterDataState<Nothing>()
}
