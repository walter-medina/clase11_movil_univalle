package com.example.clase8.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id:Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image:String
)
