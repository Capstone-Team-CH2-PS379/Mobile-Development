package com.example.fluentin.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.fluentin.data.remote.network.ApiService
import com.example.fluentin.data.remote.response.DataAllNative
import com.example.fluentin.data.remote.response.GeneralResponse
import com.example.fluentin.data.remote.response.LeaderboardData
import com.example.fluentin.data.remote.response.LeaderboardItem
import com.example.fluentin.data.remote.response.LeaderboardResponse
import com.example.fluentin.data.remote.response.LeaderboardResponseDetail
import com.example.fluentin.data.remote.response.LoginRequest
import com.example.fluentin.data.remote.response.LoginResponse
import com.example.fluentin.data.remote.response.RecordRequest
import com.example.fluentin.data.remote.response.RegisterRequest
import com.example.fluentin.data.remote.response.SkorResponse
import com.example.fluentin.data.remote.response.recordResponse

class AppRepository (
    private val apiService: ApiService
) {

    companion object {
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService
        ): AppRepository = instance ?: synchronized(this) {
            instance ?: AppRepository(apiService)
        }
    }

    fun login(username: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        val loginRequestBody = LoginRequest(username,password)
        emit(Result.Loading)
        try {
            val data = apiService.loginRequest(loginRequestBody)
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.d("Story Repository", "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): LiveData<Result<GeneralResponse>> = liveData {
        val registRequestBody = RegisterRequest(firstName,lastName,email,password)
        emit(Result.Loading)
        try {
            val data = apiService.registerRequest(registRequestBody)
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.d("Register", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun uploadRecord(
        userId: String,
        nativeAudioId: String,
        skor: String,
    ): LiveData<Result<recordResponse>> = liveData {
        val recordRequestBody = RecordRequest(userId, nativeAudioId, skor)
        emit(Result.Loading)
        try {
            val data = apiService.recordRequest(recordRequestBody)
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.d("Record", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }


    fun updateProfile(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): LiveData<Result<GeneralResponse>> = liveData {
        val registRequestBody = RegisterRequest(firstName,lastName,email,password)
        emit(Result.Loading)
        try {
            val data = apiService.registerRequest(registRequestBody)
            emit(Result.Success(data))
        } catch (e: Exception) {
            Log.d("Register", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }


    fun getSkorUser(id:String): LiveData<Result<SkorResponse>> = liveData{
        emit(Result.Loading)
        try {
            val data = apiService.getUserSkor(id)
            emit(Result.Success(data))
        } catch (e : Exception){
            Log.d("Skor", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }


    fun getAllNative() : LiveData<Result<List<DataAllNative>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getNativeData()
            val allNativeResponse = response.data
            emit(Result.Success(allNativeResponse))
        }catch (e:Exception){
            Log.d("AllNative", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }


    fun getAllLeaderboard() : LiveData<Result<List<LeaderboardData>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllLeaderboard()
            val allLeaderboardResponse= response.data
            emit(Result.Success(allLeaderboardResponse))
        }catch (e:Exception){
            Log.d("All Leanderboard", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }




//    fun getDetailLeaderboard(id:String): LiveData<Result<LeaderboardResponse>> = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.getDetailLeaderboard(id)
//            emit(Result.Success(response))
//        }catch (e:Exception){
//            Log.d("DetailLeaderboard", e.message.toString())
//            emit(Result.Error(e.toString()))
//        }
//    }

    fun getDetailLeaderboard(id: String): LiveData<Result<LeaderboardResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailLeaderboard(id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("DetailLeaderboard", e.message.toString())
            emit(Result.Error(e.toString()))
        }
    }





}