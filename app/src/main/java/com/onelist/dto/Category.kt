package com.onelist.dto

/**
 *@author Tony Gentile, Ryan Durham
 * @property categoryId the id of the category
 * @property name the names of of the categories
 *@constructor Creates a category class to hold category data
 */
data class Category(val categoryId: Int, var name: String) {
    /**
     *@return a string representation of the category class in specific format
     */
    override fun toString(): String {
        return "$categoryId - $name"
    }
}