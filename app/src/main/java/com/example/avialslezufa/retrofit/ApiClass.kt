package com.example.avialslezufa.retrofit

import android.telecom.Call
import kotlinx.coroutines.CoroutineScope
import retrofit2.http.GET
import retrofit2.http.*

interface ApiClass{
    @GET("services")
    suspend fun getInf(): Servi
}