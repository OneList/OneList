package com.onelist.dto

data class Category(val categoryId: Int, var name: String) {

    override fun toString(): String {
        return "$categoryId - $name"
    }
}