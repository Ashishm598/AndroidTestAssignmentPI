package com.assignment.shadiandroidtest.ui.main

import androidx.lifecycle.LiveData
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.global.GlobalPresenter
import com.assignment.shadiandroidtest.global.GlobalView
import com.assignment.shadiandroidtest.models.MainResponse
import io.reactivex.Observable
import retrofit2.Response
import java.util.*

interface MainActivityContractMVP {

    interface View : GlobalView {
        fun loadUserDataInAdapter(users: MutableList<UserEntity>)
        fun updateUserInAdapter(user: UserEntity, adapterPosition: Int)
        fun toggleProgressBar(status :Boolean)
    }

    interface Presenter : GlobalPresenter {
        fun loadUserData()
    }

    interface Model {
        fun getUserDataFromAPI(): Observable<Response<MainResponse?>?>?
        fun getUserDataFromDB(): MutableList<UserEntity>
        fun updateUserInDB(userEntity: UserEntity, onSuccess: () -> Unit)
        fun saveAllUserInDB(users: MutableList<UserEntity>)
    }


}