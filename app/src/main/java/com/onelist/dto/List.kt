package com.onelist.dto

import kotlin.collections.List

<<<<<<< Updated upstream:app/src/main/java/com/onelist/dto/List.kt
<<<<<<< Updated upstream:app/src/main/java/com/onelist/dto/List.kt
class List<T> {
=======
data class ShoppingList(var listID: Int, var name: String) {
>>>>>>> Stashed changes:app/src/main/java/com/onelist/dto/ShoppingList.kt
=======
data class ShoppingList(var listID: Int, var name: String, var list: ShoppingList) {

>>>>>>> Stashed changes:app/src/main/java/com/onelist/dto/ShoppingList.kt
    //list holds Items which are strings
    private val stringList = mutableListOf<String>()

    fun addString(string: String) {
        stringList.add(string)
    }

    fun getStringList(): List<String> {
        return stringList.toList()
    }
}