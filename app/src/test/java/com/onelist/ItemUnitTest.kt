package com.onelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.onelist.dto.Item
import com.onelist.service.ItemService
import junit.framework.TestCase.*
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ItemUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var itemService: ItemService
    var allBuildings: List<Item>? = ArrayList<Item>()

    @Test
    fun `Given item data is available when I search for ramen then I should receive ramen`() =
        runTest {
            givenItemServiceIsInitialized()
            whenItemDataIsReadAndParsed()
            thenItemCollectionShouldContainTeacherDyer()
        }

    private fun givenItemServiceIsInitialized() {
        itemService = ItemService()
    }

    private suspend fun whenItemDataIsReadAndParsed() {
        allBuildings = itemService.fetchItems()
    }

    private fun thenItemCollectionShouldContainTeacherDyer() {
        assertNotNull(allBuildings)
        assertTrue(allBuildings!!.isNotEmpty())
        var containsRamen = false
        allBuildings!!.forEach {
            if (it.name.equals("ramen")) {
                containsRamen = true
            }
        }
        assertTrue(containsRamen)
    }
}