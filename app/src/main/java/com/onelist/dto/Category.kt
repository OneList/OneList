package com.onelist.dto

data class Category(val categoryID: Int, var name: String) {

    override fun toString(): String {
        return "$categoryID - $name"
    }
}