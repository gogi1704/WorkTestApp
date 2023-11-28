package com.l_george.worktestapp.data.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.l_george.worktestapp.data.models.PaymentModel

@Entity
data class PaymentEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title: String,
    val amount: Double,
    val created: Int,
)

fun PaymentEntity.toModel():PaymentModel =
    PaymentModel(
        id, title, amount, created
    )