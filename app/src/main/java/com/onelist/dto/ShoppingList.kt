package com.onelist.dto

/**
 * Class to hold shopping list data
 *
 * @property listId the ID of the shopping list the object references
 * @property name the name associated with the shopping list
 * @property userId the ID of of the user associated with the list
 * @constructor Creates a shopping list with ID, name, and user ID
 */
data class ShoppingList(var listID: Int, var name: String, var userID: Int) {

    override fun toString(): String {
        return "$listID - $name ($userID)"
    }
}