package com.onelist.dto

data class Item(var itemId: Int, var name: String, var categoryId: Int, var quantity : Int) {

    override fun toString(): String {
        return "$itemId - $name ($quantity)"
    }
}