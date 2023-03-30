package com.onelist

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.onelist.dto.*
import com.onelist.service.IItemService
import com.onelist.service.ItemService
import com.onelist.service.UserService
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainViewModel(var itemService: IItemService = ItemService()) : ViewModel() {
    var items : MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    var selectedItem by mutableStateOf(Item())
    var shoppingLists : MutableLiveData<List<ShoppingList>> = MutableLiveData<List<ShoppingList>>()
    var userService : UserService = UserService()
    var user: User? = null

    private lateinit var firestore : FirebaseFirestore


    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToItems()
    }

    private fun listenToItems() {
        firestore.collection("items").addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.w(
                    "MainViewModel.listenToSpecimens()",
                    "Error: could not recieve items ${error.message}"
                )
                return@addSnapshotListener
            }
            snapshot.let {
                val allItems = ArrayList<Item>()
                val documents = snapshot!!.documents
                documents.forEach { document ->
                    val item = document.toObject(Item::class.java)
                    item?.let { allItems.add(it) }
                }
                items.value = allItems
            }
        }
    }

    fun fetchItems() {
        viewModelScope.launch {
            var innerItems = emptyList<Item>()
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
        handle.addOnSuccessListener { Log.i("Firebase", "Item saved") }
        handle.addOnFailureListener { Log.e("Firebase", "Item save failed $it") }
    }

    fun deleteItem(item: Item) {
        val document = firestore.collection("items").document(item.itemID)
        val handle = document.delete()
        handle.addOnSuccessListener { Log.i("Firebase", "Item deleted") }
        handle.addOnFailureListener { Log.e("Firebase", "Item delete failed $it") }

    }

    fun saveCategory(category: Category) {
        val document = if (category.categoryID.isEmpty()) {
            firestore.collection("categories").document()
        } else {
            firestore.collection("categories").document(category.categoryID)
        }
        category.categoryID = document.id
        val handle = document.set(category)
        handle.addOnSuccessListener { Log.i("Firebase", "Category Saved") }
        handle.addOnFailureListener { Log.e("Firebase", "Category save failed $it") }
    }

    fun saveShoppingList(shoppingList: ShoppingList) {
        val document = if (shoppingList.listID.isEmpty()) {
            firestore.collection("lists").document()
        } else {
            firestore.collection("lists").document(shoppingList.listID)
        }
        shoppingList.listID = document.id
        val handle = document.set(shoppingList)
        handle.addOnSuccessListener { Log.i("Firebase", "Shopping List Saved") }
        handle.addOnFailureListener { Log.e("Firebase", "Shopping list save failed $it") }
    }

    fun saveUser(user: User) {
        val document = if (user.userID.isEmpty()) {
            firestore.collection("users").document()
        } else {
            firestore.collection("users").document(user.userID)
        }
        user.userID = document.id
        val handle = document.set(user)
        handle.addOnSuccessListener { Log.i("Firebase", "User Saved") }
        handle.addOnFailureListener { Log.e("Firebase", "User save failed $it") }
    }

    fun validateItemInfoInDialog(itemName: String, itemQuantity: String): Pair<Boolean, Int>{
        if(itemName.isEmpty() || itemQuantity.isEmpty()){
            return Pair(false, R.string.enter_fields)
        }
        try{
            itemQuantity.toInt()
            if(itemQuantity.toInt() < 1){
                return Pair(false, R.string.enter_one)
            }
        }catch(ex: NumberFormatException){
            return Pair(false, R.string.enter_number)
        }

        return Pair(true, 0)
    }


}