package com.onelist

import com.onelist.dto.User
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserUnitTest {

    @Test
    fun `given a user dto when userID is 1, firstname is John, lastName is Smith, and email is johnsmith@testcom then the toString will return "1 - John Smith (johnsmith@testcom)"`() {
        val user = User(1, "John", "Smith", "johnsmith@test.com")
        assertEquals("1 - John Smith (johnsmith@test.com)", user.toString())
    }
}