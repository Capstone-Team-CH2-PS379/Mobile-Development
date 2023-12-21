package com.example.fluentin.ui.onbarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.fluentin.R
import com.example.fluentin.data.PreferenceDataSource
import com.example.fluentin.databinding.ActivitySplashScreenBinding
import com.example.fluentin.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    private val pref : PreferenceDataSource by lazy{
        PreferenceDataSource.invoke(this)
    }
    private val delay = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = pref.fetchAuthToken()
        Handler(Looper.getMainLooper()).postDelayed({
            if (token.isNullOrEmpty()) {
                startActivity(Intent(this@SplashScreenActivity, OnBoardingActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            }
            finish()
        }, delay)
    }
}