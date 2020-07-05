package com.assignment.shadiandroidtest.app.di

import com.assignment.shadiandroidtest.interactors.UserEntityInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object InteractorModules  {

    @Provides
    @Singleton
    fun provideUserInterractor(): UserEntityInteractor {
        return UserEntityInteractor()
    }

}