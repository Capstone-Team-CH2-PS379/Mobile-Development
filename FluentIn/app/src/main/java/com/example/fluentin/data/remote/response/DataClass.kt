package com.example.fluentin.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class RegisterRequest(
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)


data class UpdateRequest(
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
)

data class RecordRequest(
    val userId:String,
    val nativeAudioId:String,
    val skor:String,
)

@Parcelize
data class Kategori(
    val kategori_name: String,
) : Parcelable


@Parcelize
data class Conversations(
    val room_name: String,
    val deskription: String
) : Parcelable


@Parcelize
data class NgobrolBareng(
    val title: String,
    val Image: Int,
) : Parcelable

@Parcelize
data class Channel(
    val name: String,
    val Image: Int,
) : Parcelable


