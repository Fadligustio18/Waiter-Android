package com.example.firshapp.model

import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("isAvaible")
    val isAvaible: Boolean,
    @SerializedName("deskripsi")
    val deskripsi: String
)