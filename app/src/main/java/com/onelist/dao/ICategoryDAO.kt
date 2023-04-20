package com.onelist.dao

import com.onelist.dto.Category

interface ICategoryDAO {
    fun addCategory(category: Category): Category
    fun getCategory(categoryID: String): Category?
    fun updateCategory(category: Category): Category
    fun deleteCategory(categoryID: String): Boolean
    fun getAllCategories(): List<Category>
}