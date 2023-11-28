package com.l_george.worktestapp.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.l_george.worktestapp.data.dataBase.entities.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentsDao {

    @Query("SELECT * FROM PaymentEntity")
    fun getPayments(): Flow<List<PaymentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayments(payments: List<PaymentEntity>)
}