package com.example.fluentin.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class LoginResult(

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("token")
	val token: String
)


data class GeneralResponse(
	@field:SerializedName("error")
	val error: Boolean,
	@field:SerializedName("message")
	val message: String
)

data class recordResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("recordingId")
	val recordingId: String,

	@field:SerializedName("skor")
	val skor: String,
)



data class ResponseAllNative(
	@field:SerializedName("data")
	val data: List<DataAllNative>
)

data class SkorResponse(
	@SerializedName("message")
	val message: String,

	@SerializedName("data")
	val data: SkorData
)

data class SkorData(
	@SerializedName("user_id")
	val userId: Int,

	@SerializedName("full_name")
	val fullName: String,

	@SerializedName("skor")
	val skor: String
)


data class LeaderboardResponse(
	@SerializedName("data")
	val data: List<LeaderboardData>
)

data class LeaderboardData(
	val user_id: Int,
	val full_name: String,
	val skor: String
)



data class LeaderboardResponseDetail(
	@SerializedName("data")
	val data: DataLeaderboard
)

data class DataLeaderboard(
	@SerializedName("user_id")
	val user_id: Int,

	@SerializedName("full_name")
	val full_name: String,

	@SerializedName("skor")
	val skor: String
)



data class ResponseNative(

	@field:SerializedName("data")
	val data: List<DataNativeItem>,

	@field:SerializedName("message")
	val message: String
)


data class DataNativeItem(

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("text_audio")
	val textAudio: String,

	@field:SerializedName("text_translate")
	val textTranslate: String,

	@field:SerializedName("native_audio_id")
	val nativeAudioId: Int,

	@field:SerializedName("audioPath")
	val audioPath: String
)

data class UpdateUserResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: List<LoginResult>

)

//data class UpdateUserResponse(
//	val message: String,
//	val data: UserData
//)




data class DataAllNative(
	val native_audio_id: Int,
	val category_id: Int,
	val text_audio: String,
	val audioPath: String,
	val text_translate: String
)

data class LeaderboardItem(
	val userId: Int,
	val fullName: String,
	val skor: String
)






