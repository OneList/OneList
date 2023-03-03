package com.onelist

import com.onelist.dto.Category
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CategoryUnitTest {

    @Test
    fun `given a category dto when categoryID is 1 and name is Category Name then toString() returns "1 - Category Name"`() {
        val category = Category(1, "Category Name")
        assertEquals("1 - Category Name", category.toString())
    }

    @Test
    fun `given a category dto when categoryID is 2 and name is Bathroom then categoryID is 2 and name is Bathroom`() {
        var category = Category(2, "Bathroom")
        assertTrue(category.categoryID == 2)
        assertTrue(category.name == "Bathroom")
    }
}