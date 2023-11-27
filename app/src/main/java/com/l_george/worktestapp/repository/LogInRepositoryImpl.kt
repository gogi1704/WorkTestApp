package com.l_george.worktestapp.repository

import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.auth.TestAuth
import com.l_george.worktestapp.data.UserModel
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val auth: TestAuth
) :
    LogInRepository {

    override suspend fun logIn(userModel: UserModel) {
        val response = apiService.logIn(userModel)
        if (response.isSuccessful) {
            auth.saveToken(response.body()?.response?.token ?: throw Exception())
        } else {
            throw Exception()
        }
    }

    override suspend fun getPayments() {
        val response = apiService.getPayments()
        if (response.isSuccessful) {
            response.body()
        } else {
            response
        }
    }


}