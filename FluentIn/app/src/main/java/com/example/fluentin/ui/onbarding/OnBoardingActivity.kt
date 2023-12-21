package com.example.fluentin.ui.onbarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fluentin.data.PreferenceDataSource
import com.example.fluentin.databinding.ActivityOnBoardingBinding
import com.example.fluentin.ui.login.LoginActivity
import com.example.fluentin.ui.main.MainActivity
import com.example.fluentin.ui.register.RegisterActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnBoardingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            btnLogin.setOnClickListener {
                val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            btnRegister.setOnClickListener {
                val intent = Intent(this@OnBoardingActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}