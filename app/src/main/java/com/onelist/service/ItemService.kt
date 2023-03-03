package com.onelist.service

import com.onelist.RetrofitClientInstance
import com.onelist.dao.IItemDAO
import com.onelist.dto.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

interface IItemService {}

class ItemService : IItemService {
    suspend fun fetchItems(): List<Item>? {
        return withContext(Dispatchers.IO) {
            val service = RetrofitClientInstance.retrofitInstance?.create(IItemDAO::class.java)
            val items = async {
                service?.getAllItems()
            }
            var result = items.await()?.awaitResponse()?.body()
            return@withContext result
        }
    }
}