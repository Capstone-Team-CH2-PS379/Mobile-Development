package com.example.fluentin.data

import android.content.Context
import android.content.SharedPreferences

object UserSharedPreferences {
    private const val PREF_NAME = "MyAppPreferences"
    private const val KEY_USER_ID = "userId"
    private const val KEY_USER_POINTS = "user_points"



    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserId(context: Context, userId: Int) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.edit().putString(KEY_USER_ID, userId.toString()).apply()
    }

    fun getUserId(context: Context): String {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(KEY_USER_ID, null).toString()
    }


    fun saveUserPoints(context: Context, key: String, points: Int) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.edit().putInt(key, points).apply()
    }

    fun getUserPoints(context: Context, key: String): Int {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getInt(key, 0)
    }


}