package com.onelist

import com.onelist.dto.ShoppingList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShoppingListUnitTest {

    @Test
    fun `given a shoppinglist dto when listID is 1, name is My List, and userID is 4 then toString() will return "1 - My List (4)"`() {
        val shoppingList = ShoppingList(1, "My List", 4)
        assertEquals("1 - My List (4)", shoppingList.toString())
    }
}