package com.example.firshapp.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("isAvaible")
    val isAvaible: Boolean,
    @SerializedName("createdAt")
    val createdAt: String
)