package com.l_george.worktestapp.data.dataBase.di

import android.content.Context
import androidx.room.Room
import com.l_george.worktestapp.data.dataBase.AppDb
import com.l_george.worktestapp.data.dataBase.dao.PaymentsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    companion object {
        private const val DB_NAME = "APP_DB"
    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDb =
        Room.databaseBuilder(context, AppDb::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun providePaymentDao(appDb: AppDb): PaymentsDao = appDb.paymentDao()


}