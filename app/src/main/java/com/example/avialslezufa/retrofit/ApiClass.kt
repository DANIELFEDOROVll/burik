package com.example.avialslezufa.retrofit

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.*

interface ApiClass{
    @GET("/services")
    fun getInf(): List<Service>
}