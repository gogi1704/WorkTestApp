package com.l_george.worktestapp.api

import com.l_george.worktestapp.data.dataFromApi.PaymentResponseData
import com.l_george.worktestapp.data.dataFromApi.TokenResponseData
import com.l_george.worktestapp.data.models.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun logIn(@Body user: UserModel): Response<TokenResponseData>

    @GET("payments")
    suspend fun getPayments():Response<PaymentResponseData>
}