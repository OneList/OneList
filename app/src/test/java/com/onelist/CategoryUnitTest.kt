package com.onelist

import com.onelist.dto.Category
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CategoryUnitTest {

    @Test
    fun categoryToString() {
        val category = Category("1", "Category Name")
        assertEquals("1 - Category Name", category.toString())
    }
}