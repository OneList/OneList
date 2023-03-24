package com.onelist

import com.onelist.dto.User
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserUnitTest {

    @Test
    fun userToString() {
        val user = User("1", "John")
        assertEquals("1 - John", user.toString())
    }
}