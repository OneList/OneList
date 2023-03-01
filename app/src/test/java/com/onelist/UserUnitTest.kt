package com.onelist

import com.onelist.dto.User
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserUnitTest {

    @Test
    fun `toString returns "1 - John Smith (johnsmith@test com)" when userID = 1, firstName = "John", lastName = "Smith", & email = "johnsmith@test com"`() {
        val user = User(1, "John", "Smith", "johnsmith@test.com")
        assertEquals("1 - John Smith (johnsmith@test.com)", user.toString())
    }
}