package com.capstone.fluentin.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.capstone.fluentin.data.Result
import com.capstone.fluentin.data.model.User
import com.capstone.fluentin.data.network.api.ApiService
import com.capstone.fluentin.data.network.response.SignInResponse
import com.capstone.fluentin.data.network.response.SignUpResponse
import com.capstone.fluentin.data.preferences.UserPreferences
import com.capstone.fluentin.data.request.LoginRequest
import com.capstone.fluentin.data.request.RegisterRequest

class UserRepository(private val pref: UserPreferences, private val apiService: ApiService) {

    fun userLogin(email: String, password: String): LiveData<Result<SignInResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.login(LoginRequest(email, password))
            emit(Result.Success(response))
        } catch (e: Exception){
            Log.d("Login", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun userRegister(
    firstName: String,
    lastName: String,
    email: String,
    password: String
    ): LiveData<Result<SignUpResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(RegisterRequest(firstName, lastName, email, password))
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("Signup", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getUserData(): LiveData<User> {
        return pref.getUserData().asLiveData()
    }

    suspend fun saveUserData(user: User) {
        pref.saveUserData(user)
    }

    suspend fun login() {
        pref.login()
    }

    suspend fun logout() {
        pref.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            preferences: UserPreferences,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(preferences, apiService)
            }.also { instance = it }
    }

}