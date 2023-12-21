package com.example.fluentin.ui.detailleaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fluentin.data.AppRepository
import com.example.fluentin.data.Result
import com.example.fluentin.data.remote.response.LeaderboardData

class DetailLeaderboardViewModel(private val repository: AppRepository) : ViewModel() {
    fun getDetailLeaderboards(id:String) = repository.getDetailLeaderboard(id)

//    fun getDetailLeaderboards(id: String): LiveData<Result<LeaderboardData>> =
//        repository.getDetailLeaderboard(id)
}