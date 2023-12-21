package com.example.fluentin.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fluentin.data.AppRepository
import com.example.fluentin.data.di.Injection
import com.example.fluentin.ui.detailleaderboard.DetailLeaderboardActivity
import com.example.fluentin.ui.detailleaderboard.DetailLeaderboardViewModel
import com.example.fluentin.ui.leaderboard.LeaderboardViewModel
import com.example.fluentin.ui.login.LoginViewModel
import com.example.fluentin.ui.profile.ProfileViewModel
import com.example.fluentin.ui.pronunciation.PronunciationViewModel
import com.example.fluentin.ui.register.RegisterViewModel

class ViewModelFactory private constructor(
    private val repository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideRepository(context),
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PronunciationViewModel::class.java) -> {
                PronunciationViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LeaderboardViewModel::class.java) -> {
                LeaderboardViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailLeaderboardViewModel::class.java) -> {
                DetailLeaderboardViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}