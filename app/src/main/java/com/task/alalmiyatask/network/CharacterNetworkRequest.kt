package com.task.alalmiyatask.network

import okhttp3.ResponseBody
import retrofit2.http.GET

interface CharacterNetworkRequest {
    @GET("/")
    suspend fun getResponseBodyHtml() : ResponseBody
}