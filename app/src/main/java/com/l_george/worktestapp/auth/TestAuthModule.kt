package com.l_george.worktestapp.auth

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestAuthModule {
    @Provides
    @Singleton
    fun provideTestAuth(@ApplicationContext context: Context):TestAuth = TestAuth(context)
}