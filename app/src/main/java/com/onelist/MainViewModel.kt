package com.onelist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.onelist.dto.Item
import com.onelist.service.ItemService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var items : MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    var itemService : ItemService = ItemService()

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

    fun saveItem(item: Item) {
        val document = if (item.itemID == null || item.itemID.isEmpty()) {
            firestore.collection("items").document()
        } else {
            firestore.collection("items").document(item.itemID)
        }
        item.itemID = document.id
        val handle = document.set(item)
        handle.addOnSuccessListener { Log.d("Firebase", "Item Saved") }
        handle.addOnFailureListener { Log.d("Firebase", "Item save failed $it") }
    }
}