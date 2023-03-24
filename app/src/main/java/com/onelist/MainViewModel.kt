package com.onelist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.onelist.dto.*
import com.onelist.service.ItemService
import com.onelist.service.UserService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var items : MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    var shoppingLists : MutableLiveData<List<ShoppingList>> = MutableLiveData<List<ShoppingList>>()
    var itemService : ItemService = ItemService()
    var userService : UserService = UserService()

    private lateinit var firestore : FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun fetchItems() {
        viewModelScope.launch {
            var innerItems = itemService.fetchItems()
            items.postValue(innerItems)
        }
    }

    fun fetchShoppingLists() {
        viewModelScope.launch {
            var results = userService.fetchShoppingLists()
            shoppingLists.postValue(results)
        }
    }

    fun saveItem(item: Item) {
        val document = if (item.itemID.isEmpty()) {
            firestore.collection("items").document()
        } else {
            firestore.collection("items").document(item.itemID)
        }
        item.itemID = document.id
        val handle = document.set(item)
        handle.addOnSuccessListener { Log.d("Firebase", "Item Saved") }
        handle.addOnFailureListener { Log.d("Firebase", "Item save failed $it") }
    }

    fun saveCategory(category: Category) {
        val document = if (category.categoryID.isEmpty()) {
            firestore.collection("categories").document()
        } else {
            firestore.collection("categories").document(category.categoryID)
        }
        category.categoryID = document.id
        val handle = document.set(category)
        handle.addOnSuccessListener { Log.d("Firebase", "Category Saved") }
        handle.addOnFailureListener { Log.d("Firebase", "Category save failed $it") }
    }

    fun saveShoppingList(shoppingList: ShoppingList) {
        val document = if (shoppingList.listID.isEmpty()) {
            firestore.collection("lists").document()
        } else {
            firestore.collection("lists").document(shoppingList.listID)
        }
        shoppingList.listID = document.id
        val handle = document.set(shoppingList)
        handle.addOnSuccessListener { Log.d("Firebase", "Shopping List Saved") }
        handle.addOnFailureListener { Log.d("Firebase", "Shopping list save failed $it") }
    }

    fun saveUser(user: User) {
        val document = if (user.userID.isEmpty()) {
            firestore.collection("users").document()
        } else {
            firestore.collection("users").document(user.userID)
        }
        user.userID = document.id
        val handle = document.set(user)
        handle.addOnSuccessListener { Log.d("Firebase", "User Saved") }
        handle.addOnFailureListener { Log.d("Firebase", "User save failed $it") }
    }
}