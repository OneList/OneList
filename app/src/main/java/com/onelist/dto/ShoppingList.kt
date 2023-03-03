package com.onelist.dto

data class ShoppingList(var listID: Int, var name: String, var userID: Int) {

    override fun toString(): String {
        return "$listID - $name ($userID)"
    }
}