package com.task.alalmiyatask.base

import retrofit2.Response

interface MVPBaseInterActorOutput<T> {
    fun onServiceRunning()
    fun onResponseSuccess(response : Response<T>)
    fun onResponseError(response: Response<T>)
    fun onResponseFailure(t:Throwable)
}