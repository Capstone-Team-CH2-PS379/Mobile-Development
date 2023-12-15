package com.capstone.fluentin.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.fluentin.data.network.api.ApiConfig
import com.capstone.fluentin.data.preferences.UserPreferences
import com.capstone.fluentin.data.repository.UserRepository

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("fluentin")
object Injection {
    fun provideRepository(context: Context): UserRepository {
        val preferences = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiClient()
        return UserRepository.getInstance(preferences, apiService)
    }
}