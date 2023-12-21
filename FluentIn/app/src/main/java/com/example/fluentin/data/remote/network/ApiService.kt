package com.example.fluentin.data.remote.network

import com.example.fluentin.data.remote.response.GeneralResponse
import com.example.fluentin.data.remote.response.LeaderboardData
import com.example.fluentin.data.remote.response.LeaderboardResponse
import com.example.fluentin.data.remote.response.LeaderboardResponseDetail
import com.example.fluentin.data.remote.response.LoginRequest
import com.example.fluentin.data.remote.response.LoginResponse
import com.example.fluentin.data.remote.response.RecordRequest
import com.example.fluentin.data.remote.response.RegisterRequest
import com.example.fluentin.data.remote.response.ResponseAllNative
import com.example.fluentin.data.remote.response.SkorResponse
import com.example.fluentin.data.remote.response.UpdateUserResponse
import com.example.fluentin.data.remote.response.recordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("users/register")
    suspend fun registerRequest(@Body requestBody: RegisterRequest): GeneralResponse

    @POST("users/login")
    suspend fun loginRequest(@Body requestBody: LoginRequest): LoginResponse

    @POST("record")
    suspend fun recordRequest(@Body requestBody: RecordRequest): recordResponse

    @GET("native")
    suspend fun getNativeData(): ResponseAllNative

    @GET("skor/leaderboard")
    suspend fun getAllLeaderboard(): LeaderboardResponse


    @GET("skor/{id}")
    suspend fun getDetailLeaderboard(
        @Path("id") id: String
    ): LeaderboardResponse

    @GET("native/{id}")
    suspend fun getNativeById(
        @Path("id") id : String
    ) : ResponseAllNative

    @GET("skor/{id}")
    suspend fun getUserSkor(
        @Path("id") id : String
    ) : SkorResponse

    @PATCH("users/{id}")
    fun updateUser(
        @Path("id") userId: String,
        @Body updateRequestBody: UpdateUserRequestBody
    ): Call<UpdateUserResponse>


    data class UpdateUserRequestBody(
        val first_name: String,
        val last_name: String,
        val email: String,
        val password: String
    )



}