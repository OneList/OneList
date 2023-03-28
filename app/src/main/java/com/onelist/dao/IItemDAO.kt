package com.onelist.dao


import com.onelist.dto.Item
import retrofit2.Call
import retrofit2.http.GET

interface IItemDAO {
    @GET("/OneList/OneList/Tony/sampledata.json")
    fun getAllItems() : Call<ArrayList<Item>>
}