package com.l_george.worktestapp.repository.paymentsRepository

import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.data.dataBase.dao.PaymentsDao
import com.l_george.worktestapp.data.dataFromApi.toPaymentModel
import javax.inject.Inject

class PaymentsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: PaymentsDao
) : PaymentsRepository {


    override suspend fun getPayments() {
        val response = apiService.getPayments()
        if (response.isSuccessful) {
            val list = response.body()?.response?.map { it.toPaymentModel() }
            list
        } else {
            response
        }
    }

}