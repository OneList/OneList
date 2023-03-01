package com.onelist

import com.onelist.dto.Item
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ItemUnitTest {

    @Test
    fun `toString returns "1 - Test (1)" when itemID = 1, categoryID = 1, name = "Test", & quantity = 1`() {
        val item = Item(1, "Test", 1, 1)
        assertEquals("1 - Test (1)", item.toString())
    }
}

