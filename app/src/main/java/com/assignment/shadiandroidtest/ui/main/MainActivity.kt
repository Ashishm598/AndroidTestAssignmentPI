package com.assignment.shadiandroidtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.TorrMonk.extension.log
import com.assignment.shadiandroidtest.app.Constants
import com.assignment.shadiandroidtest.app.MainApplication
import com.assignment.shadiandroidtest.databinding.ActivityMainBinding
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.interactors.UserEntityInteractor
import com.assignment.shadiandroidtest.interactors.UserEntityInteractorI
import com.assignment.shadiandroidtest.models.MainResponse
import com.assignment.shadiandroidtest.services.MainService
import com.assignment.shadiandroidtest.utils.NetworkUtil
import com.assignment.shadiandroidtest.utils.SweetDialogUtil
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainActivityContractMVVM.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var networkUtil: NetworkUtil
    private lateinit var sweetDialogUtil: SweetDialogUtil
    private lateinit var mainService: MainService
    private lateinit var userEntityInteractor: UserEntityInteractorI
    private val compositeDisposable = CompositeDisposable()


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
}
