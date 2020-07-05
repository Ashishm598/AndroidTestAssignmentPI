package com.assignment.shadiandroidtest.ui.main.di

import android.content.Context
import com.assignment.shadiandroidtest.adapters.UserAdapterListener
import com.assignment.shadiandroidtest.interactors.UserEntityInteractor
import com.assignment.shadiandroidtest.interactors.UserEntityInteractorI
import com.assignment.shadiandroidtest.services.MainService
import com.assignment.shadiandroidtest.ui.main.MainActivity
import com.assignment.shadiandroidtest.ui.main.core.MainActivityModel
import com.assignment.shadiandroidtest.ui.main.core.MainActivityPresenter
import com.assignment.shadiandroidtest.utils.NetworkUtil
import com.assignment.shadiandroidtest.utils.SweetDialogUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    @ActivityScoped
    fun provideModel(
        userEntityInteractor: UserEntityInteractor,
        mainService: MainService
    ): MainActivityModel {
        return MainActivityModel(userEntityInteractor, mainService)
    }

    @Provides
    @ActivityScoped
    fun provideSweetAlertUtil(@ActivityContext context: Context): SweetDialogUtil {
        return SweetDialogUtil(context)
    }

    @Provides
    @ActivityScoped
    fun providePresenter(
        view: MainActivity,
        model: MainActivityModel,
        networkUtil: NetworkUtil,
        sweetDialogUtil: SweetDialogUtil
    ): MainActivityPresenter {
        return MainActivityPresenter(view, model, networkUtil, sweetDialogUtil)
    }

    @Provides
    @ActivityScoped
    fun provideActivity(@ActivityContext context: Context): MainActivity {
        return context as MainActivity
    }

}
