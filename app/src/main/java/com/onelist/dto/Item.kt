package com.onelist.dto

data class Item(var id: Int, var name: String, var categoryID: Int, var quantity : Int) {

    override fun toString(): String {
        return "$id $name ($quantity)";
    }
}