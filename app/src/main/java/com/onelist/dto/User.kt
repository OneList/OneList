package com.onelist.dto

data class User(
    val userID: Int,
    var firstName: String,
    val lastName: String,
    var email: String,
    ) {

    fun getLists(): List<ShoppingList> {
        return emptyList()
    }

    override fun toString(): String {
        return "$userID - $firstName $lastName ($email)"
    }
}