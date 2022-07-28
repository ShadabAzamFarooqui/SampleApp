package com.example.sampleapp.di

import com.example.sampleapp.data.ApiInterface
import com.example.sampleapp.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        apiInterface: ApiInterface,
    ):MainRepository{
        return MainRepository(apiInterface)
    }
}