package com.assignment.shadiandroidtest.adapters

import com.assignment.shadiandroidtest.entities.user.UserEntity

interface UserAdapterListener {
    fun acceptUser(user: UserEntity, adapterPosition: Int)
    fun declineUser(user: UserEntity, adapterPosition: Int)
}