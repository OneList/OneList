package com.onelist.dao

import com.onelist.dto.Item
import com.onelist.dto.ShoppingList
import com.onelist.dto.User

interface IShoppingListDAO {
    fun addShoppingList(shoppingList: ShoppingList): ShoppingList
    fun getShoppingList(shoppingListId: String): ShoppingList?
    fun updateShoppingList(shoppingList: ShoppingList): ShoppingList
    fun deleteShoppingList(shoppingListId: String): Boolean
    fun getAllShoppingLists(): List<ShoppingList>
}