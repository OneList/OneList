package com.onelist

import com.onelist.dto.ShoppingList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShoppingListUnitTest {

    @Test
    fun `toString returns "1 - My List (4)" when listID = 1, name = "My List", & quantity = 4`() {
        val shoppingList = ShoppingList(1, "My List", 4)
        assertEquals("1 - My List (4)", shoppingList.toString())
    }
}