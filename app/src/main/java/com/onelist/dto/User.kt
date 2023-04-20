package com.onelist.dto

/**
 * Class to hold user data
 *
 * @property userID the ID of the user the object references
 * @property firstName the first name of the user
 * @property lastName the last name of the user
 * @property email the email address of the user
 * @constructor Creates a user with ID, first name, last name, and email
 */
data class User(val uid: String = "", var userID: String) {

    fun getLists(): List<ShoppingList> {
        return emptyList()
    }

    override fun toString(): String {
        return "$userID"
    }
}