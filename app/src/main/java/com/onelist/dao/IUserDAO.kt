package com.onelist.dao

interface IUserDAO {
    var firstname: String;
    var lastName: String;
    var email: String;
    fun getLists();
}
