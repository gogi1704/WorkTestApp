package com.l_george.worktestapp.repository.logInRepository

import com.google.gson.Gson
import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.auth.TestAuth
import com.l_george.worktestapp.data.models.UserModel
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val auth: TestAuth
) :
    LogInRepository {

    override suspend fun logIn(userModel: UserModel) {
        if (auth.token == null) {
            val response = apiService.logIn(userModel)
            if (response.isSuccessful) {
                auth.saveToken(response.body()?.response?.token ?: throw Exception())
            } else {
                throw Exception()
            }
        }

    }





}