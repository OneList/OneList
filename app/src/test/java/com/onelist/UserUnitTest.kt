package com.onelist

import com.onelist.dto.User
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserUnitTest {

    @Test
    fun `Given a user When the userID is 1 and the fistName is John Then the toString output should be 1 - John`() {
        val user = User("1", "John")
        assertEquals("1 - John", user.toString())
    }
}