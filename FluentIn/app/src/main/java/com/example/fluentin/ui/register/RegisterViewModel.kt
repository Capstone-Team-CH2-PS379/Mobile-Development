package com.example.fluentin.ui.register

import androidx.lifecycle.ViewModel
import com.example.fluentin.data.AppRepository

class RegisterViewModel (private val repository: AppRepository) : ViewModel() {
    fun register(
                 firstName: String,
                 lastName: String,
                 email: String,
                 password: String,
                 ) =
        repository.register(firstName,lastName,email,password)

    fun login(email:String, password:String) = repository.login(email,password)
}