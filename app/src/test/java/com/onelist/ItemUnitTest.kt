package com.onelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.onelist.dto.Item
import com.onelist.service.ItemService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ItemUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockItemService: ItemService
    var allItems: List<Item>? = ArrayList<Item>()

    private val mainThreadSurrogate = newSingleThreadContext("Main Thread")

    @Before
    fun initMocksAndMainThread() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `Given item data is available when I search for apple then I should receive apple`() =
        runTest {
            givenViewModelIsInitializedWithMockData()
            whenItemDataIsReadAndParsed()
            thenItemCollectionShouldContainApple()
        }

    private fun givenViewModelIsInitializedWithMockData() {
        val items = ArrayList<Item>()
        val apple = Item(name = "Apple")
        items.add(apple)
        val pear = Item(name = "Pear")
        items.add(pear)
        coEvery{mockItemService.fetchItems()} returns items
    }

    private suspend fun whenItemDataIsReadAndParsed() {
        allItems = mockItemService.fetchItems()
    }

    private fun thenItemCollectionShouldContainApple() {
        assertNotNull(allItems)
        assertTrue(allItems!!.isNotEmpty())
        var containsApple = false
        allItems!!.forEach {
            if (it.name.equals("Apple")) {
                containsApple = true
            }
        }
        assertTrue(containsApple)
    }
}