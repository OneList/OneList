package com.onelist.dto

import com.google.gson.annotations.SerializedName

data class Item(@SerializedName("id") var itemID: Int, var name: String, var categoryID: Int, var quantity : Int) {

    override fun toString(): String {
        return "$itemID - $name ($quantity)"
    }
}