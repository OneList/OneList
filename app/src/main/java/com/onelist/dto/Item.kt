package com.onelist.dto

/**
 * Class to hold item data
 *
 * @property itemID the ID of the item the object references
 * @property name the name of the item
 * @property categoryID the ID of the category the item belongs to
 * @property quantity the quantity of the item
 * @constructor Creates an item with ID, name, category ID, and quantity
 */
data class Item(var itemID: Int, var name: String, var categoryID: Int, var quantity : Int) {

    override fun toString(): String {
        return "$itemID - $name ($quantity)"
    }
}