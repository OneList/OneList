package com.onelist.dto

/**
 * Class to hold category data
 *
 * @property categoryID the ID of the category the object references
 * @property name the name of the category
 * @constructor Creates a category with ID and name
 */
data class Category(var categoryID: String, var name: String) {

    override fun toString(): String {
        return "$categoryID - $name"
    }
}