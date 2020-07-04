package com.assignment.shadiandroidtest.interactors

import com.assignment.shadiandroidtest.entities.user.UserEntity

interface UserEntityInteractorI {

    fun getAllUsers(): MutableList<UserEntity>

    fun isEmpty(): Boolean

    fun saveAllUsers(users: MutableList<UserEntity>)

    fun updateUser(user: UserEntity, onSuccess: () -> Unit)

}