package com.onelist.dto

import com.google.gson.annotations.SerializedName

/**
 * Class to hold item data
 *
 * @property itemID the ID of the item the object references
 * @property name the name of the item
 * @property categoryIDs the IDs of the categories the item belongs to
 * @property quantity the quantity of the item
 * @property purchased boolean value to determine if the item was purchased or not
 * @constructor Creates an item with ID, name, category ID, quantity, and purchased value
 */
data class Item(@SerializedName("id") var itemID: String = "", var name: String, var categoryIDs: List<String>, var quantity: Int, var purchased: Boolean) {

    override fun toString(): String {
        return "$itemID - $name"
    }
}