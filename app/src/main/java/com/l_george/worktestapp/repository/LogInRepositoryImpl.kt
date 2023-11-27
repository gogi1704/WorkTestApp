package com.l_george.worktestapp.repository

import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.data.UserModel
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    LogInRepository {

    override suspend fun logIn(userModel: UserModel): String {
        val response = apiService.logIn(userModel)
        if (response.isSuccessful) {
            return response.body()?.response?.token ?:throw Exception()
        } else {
            throw Exception()
        }
    }


}