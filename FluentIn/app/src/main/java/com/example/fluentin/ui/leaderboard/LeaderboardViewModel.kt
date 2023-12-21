package com.example.fluentin.ui.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fluentin.data.AppRepository
import com.example.fluentin.data.Result
import com.example.fluentin.data.remote.response.LeaderboardItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeaderboardViewModel(private val repository: AppRepository): ViewModel(){
    fun getLeaderboardList() = repository.getAllLeaderboard()
}