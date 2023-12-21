package com.example.fluentin.ui.profile

import androidx.lifecycle.ViewModel
import com.example.fluentin.data.AppRepository

class ProfileViewModel(private val repository: AppRepository) : ViewModel() {

    fun getUserSkor(id:String) = repository.getSkorUser(id)

}