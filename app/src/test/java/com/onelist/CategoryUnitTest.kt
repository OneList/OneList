package com.onelist

import com.onelist.dto.Category
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CategoryUnitTest {

    @Test
    fun `Given a category When the categoryID is 1 and the name is Breakfast Then the toString output should be 1 - Breakfast`() {
        val category = Category("1", "Breakfast")
        assertEquals("1 - Breakfast", category.toString())
    }
}