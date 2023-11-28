package com.l_george.worktestapp.data.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaymentEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title: String,
    val amount: Double,
    val created: Int,
)