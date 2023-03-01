package com.onelist.dao


import com.onelist.dto.Item
import retrofit2.Call
import retrofit2.http.GET

interface IItemDAO {
    //TODO: change link to main branch once merged
    @GET("/OneList/OneList/main/iByrd_CodeReview/ItemsJSON.txt ")
    fun getAllItems() : Call<ArrayList<Item>>
}