package com.l_george.worktestapp.repository.logInRepository

import com.l_george.worktestapp.data.models.UserModel

interface LogInRepository {
    suspend fun logIn(userModel: UserModel)
}