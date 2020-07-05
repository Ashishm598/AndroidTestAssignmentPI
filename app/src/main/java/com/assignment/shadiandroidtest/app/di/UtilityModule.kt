package com.assignment.shadiandroidtest.app.di

import android.content.Context
import com.assignment.shadiandroidtest.utils.NetworkUtil
import com.assignment.shadiandroidtest.utils.SweetDialogUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UtilityModule {

    @Provides
    @Singleton
    fun provideNetworkUtil(@ApplicationContext context: Context): NetworkUtil {
        return NetworkUtil(context)
    }

}