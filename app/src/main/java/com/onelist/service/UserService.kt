package com.onelist.service

import android.content.ContentValues.TAG
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.onelist.dao.IShoppingListDAO
import com.onelist.dto.ShoppingList
import kotlinx.coroutines.tasks.await

interface IUserService {
    suspend fun fetchShoppingLists(): List<ShoppingList>?
}

class UserService : IUserService {
    override suspend fun fetchShoppingLists(): List<ShoppingList>? {
      //TODO flesh this out
    }
}