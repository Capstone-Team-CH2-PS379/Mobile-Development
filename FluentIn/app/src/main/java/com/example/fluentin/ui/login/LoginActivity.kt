package com.example.fluentin.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fluentin.data.PreferenceDataSource
import com.example.fluentin.data.Result
import com.example.fluentin.data.UserSharedPreferences
import com.example.fluentin.databinding.ActivityLoginBinding
import com.example.fluentin.ui.main.MainActivity
import com.example.fluentin.ui.register.RegisterActivity
import com.example.fluentin.utils.ViewModelFactory
import com.example.fluentin.utils.gone
import com.example.fluentin.utils.show

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val pref by lazy {
        PreferenceDataSource.invoke(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnLogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                if (password.length >= 8){
                    viewModel.login(email, password).observe(this@LoginActivity){ result ->
                        when(result){
                            is Result.Loading -> {
                                showLoading(true)
                            }
                            is Result.Success ->{
                                showLoading(false)
                                binding.btnLogin.isEnabled = true

                                result.data.let {
                                    if (it.message == "Login successful"){
                                        pref.saveAuthToken(it.loginResult.token)
                                        message(it.message)
                                        val userId = it.loginResult.userId
                                        UserSharedPreferences.saveUserId(this@LoginActivity, userId)
                                        intent =
                                            Intent(this@LoginActivity, MainActivity::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(intent)
                                        finish()
                                    }else{
                                        val errorMessage = it.error.toString() ?: "Unknown error"
                                        message(errorMessage)
//                                        Toast.makeText(this@LoginActivity, "Email atau password salah!!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                            is Result.Error -> {
                                showLoading(false)
                                message(result.error)
                            }
                        }
                    }
                }
            }

            btnGoogle.setOnClickListener {
                Toast.makeText(this@LoginActivity, "Coming Soon!!", Toast.LENGTH_SHORT).show()
            }

            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }



    private fun message(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading:Boolean){
        if (isLoading) binding.progressBarLogin.show() else binding.progressBarLogin.gone()
    }
}