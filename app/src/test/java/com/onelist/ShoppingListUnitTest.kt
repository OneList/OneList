package com.onelist

import com.onelist.dto.ShoppingList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShoppingListUnitTest {

    @Test
    fun `Given a shoppingList When the listID is 1, the name is My List, the length is 4 and the list is 1 and 2 Then the toString output is 1 - My list (4)`() {
        val shoppingList = ShoppingList("1", "My List", listOf("4"), listOf("1", "2"))
        assertEquals("1 - My List (4)", shoppingList.toString())
    }
}