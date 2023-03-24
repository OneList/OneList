package com.onelist.dto

/**
 * Class to hold item data per instance of an item in a list
 *
 * @property lineItemId the ID of the lineItem the object references
 * @property itemId the ID of the item the object references
 * @property purchased boolean value to determine if the item was purchased or not
 * @property quantity the quantity of the item
 * @constructor Creates a lineItem with ID, purchased value, and quantity
 */
data class LineItem(var lineItemID: String, var itemId: String, var purchased: Boolean, var quantity: Int) {
    override fun toString(): String {
        return "$itemId - quantity of $quantity, purchased: $purchased"
    }
}