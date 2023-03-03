package com.onelist

import com.onelist.dto.Item
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ItemUnitTest {

    @Test
    fun `given a item dto when itemID is 1, name is Test, categoryID is 1, and quantity is 1 then toString() will return "1 - Test (1)"`(){
        val item = Item(1, "Test", 1, 1)
        assertEquals("1 - Test (1)", item.toString())
    }
}

