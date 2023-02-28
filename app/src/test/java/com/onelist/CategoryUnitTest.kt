package com.onelist

import com.onelist.dto.Category
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CategoryUnitTest {

    @Test
    fun `toString returns "1 - Category Name" when categoryID = 1 & name = "Category Name"`() {
        val category = Category(1, "Category Name")
        assertEquals("1 - Category Name", category.toString())
    }
}