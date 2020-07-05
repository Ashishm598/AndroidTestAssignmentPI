package com.assignment.shadiandroidtest.app.di

import com.assignment.shadiandroidtest.app.MainApplication
import com.assignment.shadiandroidtest.services.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideMainService(): MainService {
        return MainApplication.getMainService()
    }


}