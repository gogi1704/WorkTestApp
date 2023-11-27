package com.l_george.worktestapp.repository

import com.l_george.worktestapp.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun bindLogInRepository(apiService: ApiService):LogInRepository = LogInRepositoryImpl(apiService)
}