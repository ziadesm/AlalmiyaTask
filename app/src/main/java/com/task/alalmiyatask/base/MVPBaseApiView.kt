package com.task.alalmiyatask.base

interface MVPBaseApiView<T> {

    fun onResponseSuccess(data: T )
    fun onResponseError(errorBody: String)
    fun onResponseFailure(t : Throwable)

}
