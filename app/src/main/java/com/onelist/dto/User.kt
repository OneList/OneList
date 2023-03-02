package com.onelist.dto
/**
 *@author Tony Gentile, Ryan durham, justin tracy
 * @property userId the id of the user
 * @property firstName the first name of of user
 * @property lastName the last name of of the user
 * @property email the email of of the user
 *@constructor Creates a user class to hold user related data
 */
data class User(val userId: Int, var firstName: String, val lastName: String, var email: String) {
    /**
     * @returns a list
     */
    fun getLists(): List<ShoppingList> {
        return emptyList()
    }
    /**
     *@return a string representation of the user class in specific format
     */
    override fun toString(): String {
        return "$userId - $firstName $lastName ($email)"
    }
}