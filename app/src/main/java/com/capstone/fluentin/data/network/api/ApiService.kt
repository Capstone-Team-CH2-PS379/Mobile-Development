package com.capstone.fluentin.data.network.api

import com.capstone.fluentin.data.network.response.SignInResponse
import com.capstone.fluentin.data.network.response.SignUpResponse
import com.capstone.fluentin.data.request.LoginRequest
import com.capstone.fluentin.data.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): SignUpResponse

    @POST("users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): SignInResponse
}