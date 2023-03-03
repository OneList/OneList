package com.onelist

import com.onelist.dto.Category
import com.onelist.dto.User
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserUnitTest {

    @Test
    fun `given a user dto when userID is 1, firstname is John, lastName is Smith, and email is johnsmith@testcom then the toString will return "1 - John Smith (johnsmith@testcom)"`() {
        val user = User(1, "John", "Smith", "johnsmith@test.com")
        assertEquals("1 - John Smith (johnsmith@test.com)", user.toString())
    }

    @Test
    fun `given a user dto when userID is 2, firstname is Brayden, lastName is Cummins, and email is bc@testcom then userID is 2, firstname is Brayden, lastName is Cummins, and email is bc@testcom`() {
        val user = User(2, "Brayden", "Cummins", "bc@test.com")
        assertTrue(user.userID == 2)
        assertTrue(user.firstName == "Brayden")
        assertTrue(user.lastName == "Cummins")
        assertTrue(user.email == "bc@test.com")
    }
}