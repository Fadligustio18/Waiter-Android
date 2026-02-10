package com.example.firshapp.model

import com.google.gson.annotations.SerializedName

data class DeleteItem(
    @SerializedName("id")
    val id: Int
)