package com.l_george.worktestapp.repository.paymentsRepository

import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.data.dataBase.dao.PaymentsDao
import com.l_george.worktestapp.data.dataFromApi.toEntity
import javax.inject.Inject

class PaymentsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: PaymentsDao
) : PaymentsRepository {

    val paymentsListFlow = dao.getPayments()


    override suspend fun getPayments() {
        val response = apiService.getPayments()
        if (response.isSuccessful) {
            if (response.body()?.success == "false") {

            } else {
                dao.insertPayments(response.body()?.response?.map { it.toEntity() }!!)
            }

        } else {

        }
    }

}