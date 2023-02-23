package com.onelist.dto

data class User(val id: Int, var firstName: String, val lastName: String, var email: String) {
    fun getLists(): List<ShoppingList> {
        return emptyList()
    }

    override fun toString(): String {
        return "$id $firstName $lastName $email";
    }
}