package com.example.fluentin.ui.login

import androidx.lifecycle.ViewModel
import com.example.fluentin.data.AppRepository

class LoginViewModel (private val repository: AppRepository) : ViewModel(){
    fun login(email:String, password:String) = repository.login(email,password)
}