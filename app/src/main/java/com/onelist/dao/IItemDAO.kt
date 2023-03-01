package com.onelist.dao


import com.onelist.dto.Item
import retrofit2.Call
import retrofit2.http.GET

interface IItemDAO {
    //TODO: change link to main branch once merged
    @GET("/OneList/OneList/eaf1792706e3df73e5fc6a6cfa0a89db8b8d8d2c/app/ItemsJSON.txt")
    fun getAllItems() : Call<ArrayList<Item>>
}