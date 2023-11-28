package com.l_george.worktestapp.data.dataFromApi

import com.l_george.worktestapp.data.dataBase.entities.PaymentEntity

data class PaymentData(
    val id: String ,
    val title: String,
    val amount: String,
    val created: String,
)

fun PaymentData.toEntity(): PaymentEntity =
    PaymentEntity(
                id = try {
                    id.toInt()
                } catch (e: Exception) {
                    0
                },
                title = try {
                    title
                } catch (e: Exception) {
                    ""
                },
                amount = try {
                   amount.toDouble()

                } catch (e: Exception) {
                    0.0
                },

                created = try {
                  created.toInt()
                } catch (e: Exception) {
                    0
                },

    )

