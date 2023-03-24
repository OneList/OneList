package com.onelist.dto

/**
 * Class to hold shopping list data
 *
 * @property listId the ID of the shopping list the object references
 * @property name the name associated with the shopping list
 * @property userIDs the IDs of of the users associated with the list
 * @property itemIDs the IDs of of the line items associated with the list
 * @constructor Creates a shopping list with ID, name, and user ID
 */
data class ShoppingList(var listID: String, var name: String, var userIDs: List<String>, var itemIDs: List<String>) {
    override fun toString(): String {
        return "$listID - $name (${userIDs[0]})"
    }
}