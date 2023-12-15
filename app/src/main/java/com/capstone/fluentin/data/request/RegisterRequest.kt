package com.capstone.fluentin.data.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)