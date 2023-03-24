package com.onelist.dao

import com.onelist.dto.Item
import com.onelist.dto.User

interface IShoppingListDAO {
    var items: List<Item>
    var listId: String
    var name: String
    var users: List<User>
}