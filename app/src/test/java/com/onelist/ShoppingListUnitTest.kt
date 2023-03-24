package com.onelist

import com.onelist.dto.ShoppingList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShoppingListUnitTest {

    @Test
    fun shoppingListToString() {
        val shoppingList = ShoppingList("1", "My List", listOf("4"), listOf("1", "2"))
        assertEquals("1 - My List (4)", shoppingList.toString())
    }
}