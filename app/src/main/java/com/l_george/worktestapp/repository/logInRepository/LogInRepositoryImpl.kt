package com.l_george.worktestapp.repository.logInRepository

import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.auth.TestAuth
import com.l_george.worktestapp.data.models.UserModel
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val auth: TestAuth
) :
    LogInRepository {

     val isAuth:Boolean
         get() = auth.isAuth

    override suspend fun logIn(userModel: UserModel): Boolean {

        val response = apiService.logIn(userModel)
        if (response.isSuccessful) {
            if (response.body()?.success == "false") {
                throw Exception()
            } else {
                auth.saveToken(response.body()?.response?.token ?: throw Exception())
                return true
            }
        } else {
            throw Exception()
        }


    }


}