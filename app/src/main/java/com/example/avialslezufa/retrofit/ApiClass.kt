package com.example.avialslezufa.retrofit

import retrofit2.http.GET

interface ApiClass{
    @GET("")
    fun getInf(): Product
}