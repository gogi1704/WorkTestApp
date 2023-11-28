package com.l_george.worktestapp.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.l_george.worktestapp.data.dataBase.dao.PaymentsDao
import com.l_george.worktestapp.data.dataBase.entities.PaymentEntity

@Database([PaymentEntity::class] , version = 1)
abstract class AppDb:RoomDatabase() {
    abstract fun paymentDao():PaymentsDao
}