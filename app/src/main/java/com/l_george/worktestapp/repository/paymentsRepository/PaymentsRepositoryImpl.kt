package com.l_george.worktestapp.repository.paymentsRepository

import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.data.dataBase.dao.PaymentsDao
import com.l_george.worktestapp.data.dataBase.entities.toModel
import com.l_george.worktestapp.data.dataFromApi.toEntity
import com.l_george.worktestapp.exception.ApiException
import com.l_george.worktestapp.exception.NetworkException
import com.l_george.worktestapp.exception.UnknownException
import com.l_george.worktestapp.utils.RESPONSE_ANSWER_FALSE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PaymentsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: PaymentsDao
) : PaymentsRepository {

    val paymentsListFlow = dao.getPayments()
        .flowOn(Dispatchers.Default)
        .map { list ->
            list.map {
                it.toModel()
            }
        }


    override suspend fun getPayments() {
        try {
            val response = apiService.getPayments()
            if (response.isSuccessful) {
                if (response.body()?.success == RESPONSE_ANSWER_FALSE) {
                    throw ApiException()
                } else {
                    dao.insertPayments(response.body()?.response?.map { it.toEntity() }
                        ?: throw ApiException())
                }
            } else {
                throw ApiException()
            }
        } catch (io: IOException) {
            throw NetworkException()
        } catch (api: ApiException) {
            throw ApiException()
        } catch (e: Exception) {
            throw UnknownException()
        }


    }

}