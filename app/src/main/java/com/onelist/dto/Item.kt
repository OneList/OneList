package com.onelist.dto

/**
 *@author Tony Gentile
 * @property itemId the id of the item
 * @property name the name of of the item
 * @property categoryId the id of of the category of item
 * @property quantity the number of of the item
 *@constructor Creates a item class to hold item related data
 */
data class Item(var itemId: Int, var name: String, var categoryId: Int, var quantity : Int) {
    /**
     *@return a string representation of the item class in specific format
     */
    override fun toString(): String {
        return "$itemId - $name ($quantity)"
    }
}