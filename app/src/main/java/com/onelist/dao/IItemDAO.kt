package com.onelist.dao


import com.onelist.dto.Item
import retrofit2.Call
import retrofit2.http.GET

interface IItemDAO {
    fun addItem(item: Item): Item
    fun getItem(itemId: String): Item?
    fun updateItem(item: Item): Item
    fun deleteItem(itemId: String): Boolean
    
    @GET("/OneList/OneList/Tony/sampledata.json")
    fun getAllItems(): Call<ArrayList<Item>>
}