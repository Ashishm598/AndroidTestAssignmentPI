package com.assignment.shadiandroidtest.interactors

import com.assignment.shadiandroidtest.app.MainApplication
import com.assignment.shadiandroidtest.entities.user.UserEntity

class UserEntityInteractor : UserEntityInteractorI {

    private val box = MainApplication.getBoxStore().boxFor(UserEntity::class.java)

    override fun getAllUsers(): MutableList<UserEntity> {
        return box.all
    }

    override fun isEmpty(): Boolean {
        return box.isEmpty
    }

    override fun saveAllUsers(users: MutableList<UserEntity>) {
        box.put(users)
    }

    override fun updateUser(user: UserEntity, onSuccess: () -> Unit) {
        val id = box.put(user)
        if (id > 0) onSuccess.invoke()
    }


}