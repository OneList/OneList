package com.onelist.dao


import com.onelist.dto.Item
import retrofit2.Call

interface IItemDAO {

    fun getAllItems() : Call<ArrayList<Item>>
}