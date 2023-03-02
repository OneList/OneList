package com.onelist.dto
import kotlin.collections.List

/**
 * subject to change after class is holding data
 *@author  Ryan Durham
 * @property listId the id of the shopping list
 * @property name the name of of the shopping list
 * @property userId the id of of the user
 *@constructor Creates a shopping list class to hold a shopping list data
 */

data class ShoppingList(var listId: Int, var name: String, var userId: Int) {
    /**
     *@return a string representation of the Shopping list class in specific format
     */
    override fun toString(): String {
        return "$listId - $name ($userId)"
    }
}