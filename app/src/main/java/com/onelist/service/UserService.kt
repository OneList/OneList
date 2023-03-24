package com.onelist.service


import com.onelist.dto.ShoppingList

interface IUserService {
    suspend fun fetchShoppingLists(): List<ShoppingList>?
}

class UserService : IUserService {
    override suspend fun fetchShoppingLists(): List<ShoppingList>? {
      //TODO flesh this out
        return emptyList()
    }
}