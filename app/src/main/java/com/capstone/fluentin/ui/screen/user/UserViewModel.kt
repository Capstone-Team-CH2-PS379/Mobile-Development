package com.capstone.fluentin.ui.screen.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.fluentin.data.model.User
import com.capstone.fluentin.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {
    fun userLogin(email: String, password: String) = repository.userLogin(email, password)

    fun userRegister(firstName: String, lastName: String,email : String, password: String) =
        repository.userRegister(firstName,lastName,email, password)

    fun saveUser(user: User){
        viewModelScope.launch {
            repository.saveUserData(user)
        }
    }

    fun login(){
        viewModelScope.launch {
            repository.login()
        }
    }

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }



}