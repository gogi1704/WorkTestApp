package com.l_george.worktestapp.repository

import com.l_george.worktestapp.data.UserModel

interface LogInRepository {
    suspend fun logIn(userModel: UserModel):String
}