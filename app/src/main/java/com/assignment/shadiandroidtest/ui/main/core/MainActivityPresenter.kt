package com.assignment.shadiandroidtest.ui.main.core

import com.TorrMonk.extension.log
import com.assignment.shadiandroidtest.adapters.UserAdapterListener
import com.assignment.shadiandroidtest.constants.Status
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.models.MainResponse
import com.assignment.shadiandroidtest.ui.main.MainActivityContractMVP
import com.assignment.shadiandroidtest.utils.NetworkUtil
import com.assignment.shadiandroidtest.utils.SweetDialogUtil
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(
    private val view: MainActivityContractMVP.View,
    private val model: MainActivityContractMVP.Model,
    private val networkUtil: NetworkUtil,
    private val sweetDialogUtil: SweetDialogUtil
) : MainActivityContractMVP.Presenter, UserAdapterListener {

    private val compositeDisposable = CompositeDisposable()

    override fun loadUserData() {
        view.toggleProgressBar(true)
        val userData = model.getUserDataFromDB()
        if (userData.isEmpty()) {
            getUserFromAPI {
                model.saveAllUserInDB(it)
                view.loadUserDataInAdapter(it)
                view.toggleProgressBar(false)
            }
        } else {
            view.loadUserDataInAdapter(userData)
            view.toggleProgressBar(false)
        }

    }

    private fun getUserFromAPI(onSuccess: (MutableList<UserEntity>) -> Unit) {
        if (networkUtil.isConnectingToInternet) {
            model.getUserDataFromAPI()?.subscribe(object : Observer<Response<MainResponse?>?> {
                override fun onSubscribe(disposable: Disposable) {
                    compositeDisposable.add(disposable)
                }

                override fun onNext(response: Response<MainResponse?>) {
                    if (response.isSuccessful && response.code() == 200) {
                        val users = response.body()?.userEntities
                        if (users != null && users.size > 0) {
                            onSuccess.invoke(users)
                        }
                    } else {
                        sweetDialogUtil.showErrorSweetAlertDialog()
                    }
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    e.log("RxJavaError")
                    view.toggleProgressBar(false)
                }
            })
        } else {
            view.toggleProgressBar(false)
            sweetDialogUtil.showNoInternetWarningSweetAlertDialog()
        }
    }

    override fun onCreate() {

    }

    override fun onPause() {

    }

    override fun onResume() {

    }

    override fun onStop() {

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    override fun acceptUser(user: UserEntity, adapterPosition: Int) {
        val acceptedUser = user.apply {
            userStatus.apply {
                voted = true
                status = Status.ACCEPTED
            }
        }
        updateUser(acceptedUser, adapterPosition)
    }

    override fun declineUser(user: UserEntity, adapterPosition: Int) {
        val declinedUser = user.apply {
            userStatus.apply {
                voted = true
                status = Status.DECLINED
            }
        }
        updateUser(declinedUser, adapterPosition)
    }

    private fun updateUser(user: UserEntity, adapterPosition: Int) {
        model.updateUserInDB(user) {
            view.updateUserInAdapter(user, adapterPosition)
        }
    }

}
