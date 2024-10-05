package com.example.avialslezufa.retrofit

import com.google.gson.annotations.SerializedName


data class Tot(
    val total: Int
)
data class Servi(
    val servi: List<Service>
)
data class Service(
    val id: Int,
    val name: String,
    val price: Int
)
