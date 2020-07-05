package com.assignment.shadiandroidtest.ui.main.core

import com.assignment.shadiandroidtest.constants.Constants
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.interactors.UserEntityInteractorI
import com.assignment.shadiandroidtest.models.MainResponse
import com.assignment.shadiandroidtest.services.MainService
import com.assignment.shadiandroidtest.ui.main.MainActivityContractMVP
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainActivityModel @Inject constructor(
    private val userEntityInteractor: UserEntityInteractorI,
    private val mainService: MainService
) : MainActivityContractMVP.Model {

    override fun getUserDataFromAPI(): Observable<Response<MainResponse?>?>? {
        return mainService.getResults(Constants.RESULT_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserDataFromDB(): MutableList<UserEntity> {
        return userEntityInteractor.getAllUsers()
    }

    override fun updateUserInDB(userEntity: UserEntity, onSuccess: () -> Unit) {
        return userEntityInteractor.updateUser(userEntity) {
            onSuccess.invoke()
        }
    }

    override fun saveAllUserInDB(users: MutableList<UserEntity>) {
        return userEntityInteractor.saveAllUsers(users)
    }


}