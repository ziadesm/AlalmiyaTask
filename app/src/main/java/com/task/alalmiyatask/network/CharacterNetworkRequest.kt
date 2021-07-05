package com.task.alalmiyatask.network
import com.task.alalmiyatask.remote.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface CharacterNetworkRequest {
    @GET("/")
    suspend fun getResponseBodyHtml() : NetworkResponse<ResponseBody, Error>
}