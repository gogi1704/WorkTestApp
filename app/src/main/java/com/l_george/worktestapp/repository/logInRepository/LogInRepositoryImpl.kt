package com.l_george.worktestapp.repository.logInRepository

import com.l_george.worktestapp.api.ApiService
import com.l_george.worktestapp.auth.TestAuth
import com.l_george.worktestapp.data.models.UserModel
import com.l_george.worktestapp.exception.ApiException
import com.l_george.worktestapp.exception.AuthException
import com.l_george.worktestapp.exception.NetworkException
import com.l_george.worktestapp.exception.UnknownException
import java.io.IOException
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val auth: TestAuth
) :
    LogInRepository {

    val isAuth: Boolean
        get() = auth.isAuth

    override suspend fun logIn(userModel: UserModel): Boolean {
        try {
            val response = apiService.logIn(userModel)
            if (response.isSuccessful) {
                if (response.body()?.success == "false") {
                    throw AuthException()
                } else {
                    auth.saveToken(response.body()?.response?.token ?: throw ApiException())
                    return true
                }
            } else {
                throw ApiException()
            }

        } catch (io: IOException) {
            throw NetworkException()
        } catch (api: ApiException) {
            throw ApiException()
        } catch (auth: AuthException) {
            throw AuthException()
        } catch (e: Exception) {
            throw UnknownException()
        }


    }


}