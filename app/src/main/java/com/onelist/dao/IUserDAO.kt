package com.onelist.dao

import com.onelist.dto.User

interface IUserDAO {
    fun addUser(user: User): User
    fun getUser(userId: String): User?
    fun updateUser(user: User): User
    fun deleteUser(userId: String): Boolean
    fun getAllUsers(): List<User>
}
