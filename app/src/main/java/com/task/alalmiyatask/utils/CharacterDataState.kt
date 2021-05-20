package com.task.alalmiyatask.utils

import java.lang.Exception

sealed class CharacterDataState<out R>{
    data class Success<out T>(val data: T): CharacterDataState<T>()
    data class Error(val e: Exception): CharacterDataState<Nothing>()
    object Loading: CharacterDataState<Nothing>()
}
