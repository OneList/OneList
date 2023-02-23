package com.onelist.dto

import kotlin.collections.List

class ShoppingList {
    //list holds Items which are strings
    private val stringList = mutableListOf<String>()

    fun addString(string: String) {
        stringList.add(string)
    }

    fun getStringList(): List<String> {
        return stringList.toList()
    }
}