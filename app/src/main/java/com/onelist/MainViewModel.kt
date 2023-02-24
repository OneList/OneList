package com.onelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onelist.dto.Item
import com.onelist.service.ItemService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var items : MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    var itemService : ItemService = ItemService()

    fun fetchItems() {
        viewModelScope.launch {
            var innerItems = itemService.fetchItems()
            items.postValue(innerItems)
        }
    }
}