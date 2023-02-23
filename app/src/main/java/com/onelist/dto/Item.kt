package com.onelist.dto

data class Item(var itemID: Int, var name: String, var categoryID: Int, var quantity : Int) {

    override fun toString(): String {
        return "$itemID - $name ($quantity)"
    }
}