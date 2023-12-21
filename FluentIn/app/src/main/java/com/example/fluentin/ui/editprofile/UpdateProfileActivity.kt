package com.example.fluentin.ui.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.fluentin.R
import com.example.fluentin.data.PreferenceDataSource
import com.example.fluentin.data.UserSharedPreferences
import com.example.fluentin.databinding.ActivityUpdateProfileBinding

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateProfileBinding

    private val userSharedPreferences = UserSharedPreferences


    private val pref : PreferenceDataSource by lazy {
        PreferenceDataSource.invoke(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }


        val userId = userSharedPreferences.getUserId(this)



    }
}