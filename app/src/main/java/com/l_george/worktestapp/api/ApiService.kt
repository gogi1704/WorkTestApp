package com.l_george.worktestapp.api

import com.google.gson.JsonArray
import com.l_george.worktestapp.data.TokenResponseModel
import com.l_george.worktestapp.data.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun logIn(@Body user: UserModel): Response<TokenResponseModel>

    @GET("payments")
    suspend fun getPayments():Response<JsonArray>
}