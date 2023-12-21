package com.example.fluentin.data.di

import android.content.Context
import com.example.fluentin.data.AppRepository
import com.example.fluentin.data.remote.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): AppRepository {
        val apiService = ApiConfig.getApiService(context)
        return AppRepository.getInstance(apiService)
    }

}