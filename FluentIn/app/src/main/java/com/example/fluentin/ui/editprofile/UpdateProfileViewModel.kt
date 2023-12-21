package com.example.fluentin.ui.editprofile

import androidx.lifecycle.ViewModel
import com.example.fluentin.data.AppRepository

class UpdateProfileViewModel (private val repository: AppRepository) : ViewModel() {
    fun updateProfile(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ) =
        repository.register(firstName,lastName,email,password)

    fun login(email:String, password:String) = repository.login(email,password)
}