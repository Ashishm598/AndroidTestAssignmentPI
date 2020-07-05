package com.assignment.shadiandroidtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.TorrMonk.extension.log
import com.TorrMonk.extension.setVisibility
import com.assignment.shadiandroidtest.adapters.UserAdapter
import com.assignment.shadiandroidtest.adapters.UserAdapterListener
import com.assignment.shadiandroidtest.constants.Constants
import com.assignment.shadiandroidtest.app.MainApplication
import com.assignment.shadiandroidtest.constants.Status.ACCEPTED
import com.assignment.shadiandroidtest.constants.Status.DECLINED
import com.assignment.shadiandroidtest.databinding.ActivityMainBinding
import com.assignment.shadiandroidtest.entities.user.UserEntity
import com.assignment.shadiandroidtest.interactors.UserEntityInteractor
import com.assignment.shadiandroidtest.interactors.UserEntityInteractorI
import com.assignment.shadiandroidtest.models.MainResponse
import com.assignment.shadiandroidtest.services.MainService
import com.assignment.shadiandroidtest.ui.main.core.MainActivityModel
import com.assignment.shadiandroidtest.ui.main.core.MainActivityPresenter
import com.assignment.shadiandroidtest.utils.NetworkUtil
import com.assignment.shadiandroidtest.utils.SweetDialogUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainActivityContractMVP.View {

    private lateinit var binding: ActivityMainBinding
    private var userAdapter: UserAdapter? = null
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectDependency()

        val networkUtil = NetworkUtil(this)
        val sweetDialogUtil = SweetDialogUtil(this)
        val mainService = MainApplication.getMainService()
        val userEntityInteractor = UserEntityInteractor()

        val model = MainActivityModel(userEntityInteractor, mainService)
        presenter = MainActivityPresenter(this, model, networkUtil, sweetDialogUtil)

        presenter.loadUserData()

    }

    override fun loadUserDataInAdapter(users: MutableList<UserEntity>) {
        binding.rvUserList.setHasFixedSize(true)
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this, users, presenter)
        binding.rvUserList.adapter = userAdapter
    }

    override fun initView() {

    }

    override fun injectDependency() {

    }

    override fun updateUserInAdapter(user: UserEntity, adapterPosition: Int) {
        userAdapter?.updateUser(user, adapterPosition)
    }

    override fun toggleProgressBar(status: Boolean) {
        binding.progressBar.setVisibility(status)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
