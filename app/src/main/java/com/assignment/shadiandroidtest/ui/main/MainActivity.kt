package com.assignment.shadiandroidtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.TorrMonk.extension.log
import com.assignment.shadiandroidtest.adapters.UserAdapter
import com.assignment.shadiandroidtest.adapters.UserAdapterListener
import com.assignment.shadiandroidtest.constants.Constants
import com.assignment.shadiandroidtest.app.MainApplication
import com.assignment.shadiandroidtest.constants.Status
import com.assignment.shadiandroidtest.constants.Status.ACCEPTED
import com.assignment.shadiandroidtest.constants.Status.DECLINED
import com.assignment.shadiandroidtest.databinding.ActivityMainBinding
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.interactors.UserEntityInteractor
import com.assignment.shadiandroidtest.interactors.UserEntityInteractorI
import com.assignment.shadiandroidtest.models.MainResponse
import com.assignment.shadiandroidtest.services.MainService
import com.assignment.shadiandroidtest.utils.NetworkUtil
import com.assignment.shadiandroidtest.utils.SweetDialogUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainActivityContractMVVM.View, UserAdapterListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var networkUtil: NetworkUtil
    private lateinit var sweetDialogUtil: SweetDialogUtil
    private lateinit var mainService: MainService
    private lateinit var userEntityInteractor: UserEntityInteractorI
    private val compositeDisposable = CompositeDisposable()
    private var userAdapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkUtil = NetworkUtil(this)
        sweetDialogUtil = SweetDialogUtil(this)

        mainService = MainApplication.getMainService()
        userEntityInteractor = UserEntityInteractor()

        //  viewModel -> (model, networkUtil, sweetDialogUtil) , Model(Repository) , Repository(DB,Service)

        if (userEntityInteractor.isEmpty()) {
            loadData()
        } else {
            val users = userEntityInteractor.getAllUsers()
            loadUserDataInAdapter(users)
        }

    }

    private fun loadUserDataInAdapter(users: MutableList<UserEntity>) {
        binding.rvUserList.setHasFixedSize(true)
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this, users, this)
        binding.rvUserList.adapter = userAdapter
    }

    override fun loadData() {
        if (networkUtil.isConnectingToInternet) {

            val resultObservable = getResults()

            resultObservable?.subscribe(object : Observer<Response<MainResponse?>?> {
                override fun onSubscribe(disposable: Disposable) {
                    compositeDisposable.add(disposable)
                }

                override fun onNext(response: Response<MainResponse?>) {
                    if (response.isSuccessful && response.code() == 200) {
                        val users = response.body()?.userEntities
                        if (users != null && users.size > 0) {
                            userEntityInteractor.saveAllUsers(users)
                            loadUserDataInAdapter(users)
                        }
                    } else {
                        sweetDialogUtil.showErrorSweetAlertDialog()
                    }
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    e.log("RxJavaError")
                }
            })
        } else {
            sweetDialogUtil.showNoInternetWarningSweetAlertDialog()
        }
    }

    private fun getResults(): Observable<Response<MainResponse?>?>? {
        return mainService.getResults(Constants.RESULT_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    // ViewModel
    override fun acceptUser(user: UserEntity, adapterPosition: Int) {
        val acceptedUser = user.apply {
            userStatus.apply {
                voted = true
                status = ACCEPTED
            }
        }
        updateUserInDb(acceptedUser) {
            updateUserInAdapter(acceptedUser, adapterPosition)
        }
    }

    // View
    private fun updateUserInAdapter(user: UserEntity, adapterPosition: Int) {
        userAdapter?.updateUser(user, adapterPosition)
    }

    //ViewModel
    override fun declineUser(user: UserEntity, adapterPosition: Int) {
        val declinedUser = user.apply {
            userStatus.apply {
                voted = true
                status = DECLINED
            }
        }
        updateUserInDb(declinedUser) {
            updateUserInAdapter(declinedUser, adapterPosition)
        }
    }

    // Model
    private fun updateUserInDb(user: UserEntity, onSuccess: () -> Unit) {
        //Repo
        userEntityInteractor.updateUser(user) {
            onSuccess.invoke()
        }
    }
}
