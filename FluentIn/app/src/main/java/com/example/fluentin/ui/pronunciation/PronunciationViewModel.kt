package com.example.fluentin.ui.pronunciation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.fluentin.data.AppRepository
import kotlinx.coroutines.Dispatchers
import com.example.fluentin.data.Result
import com.example.fluentin.data.remote.response.ResponseAllNative




class PronunciationViewModel(private val repository: AppRepository) : ViewModel() {
    fun getNativeList() = repository.getAllNative()

    fun uploadRecord(
        userId : String,
        nativeAudioId : String,
        skor : String

    ) =
        repository.uploadRecord(userId, nativeAudioId, skor)

}
