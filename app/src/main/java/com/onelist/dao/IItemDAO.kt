package com.onelist.dao


import com.onelist.dto.Item
import retrofit2.Call
import retrofit2.http.GET

interface IItemDAO {
    @GET("") //TODO: add rest of retrofit link for items
    fun getAllItems() : Call<ArrayList<Item>>
}