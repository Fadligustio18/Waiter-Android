package com.example.firshapp.api

import com.example.firshapp.model.DeleteItem
import com.example.firshapp.model.Item
import com.example.firshapp.model.PostItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/api/v2/item")
    fun getItems(): Call<List<Item>>

    @POST("/api/v2/item")
    fun createItem(@Body item: PostItem): Call<Item>

    @POST("/api/v2/item/delete")
    fun deleteItem(@Body item: DeleteItem): Call<Unit>
}