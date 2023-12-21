package com.example.fluentin.ui.register

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fluentin.R
import com.example.fluentin.data.Result
import com.example.fluentin.data.PreferenceDataSource
import com.example.fluentin.data.UserSharedPreferences
import com.example.fluentin.databinding.ActivityRegisterBinding
import com.example.fluentin.ui.login.LoginActivity
import com.example.fluentin.ui.main.MainActivity
import com.example.fluentin.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    private val viewModel: RegisterViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val pref by lazy {
        PreferenceDataSource.invoke(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

            btnRegister.setOnClickListener {
               registrasi()
            }

            tvLogin.setOnClickListener {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun registrasi(){
        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val konfirPassword = binding.edtKonfirmasiPassword.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() ||konfirPassword.isEmpty()){
            if (firstName.isEmpty() && firstName.length<4){
                binding.edtFirstName.error = "Enter your first name"
            }
            if (lastName.isEmpty()){
                binding.edtLastName.error = "Enter your last name"
            }
            if (password.isEmpty()){
                binding.textInputLayoutPassword.isPasswordVisibilityToggleEnabled = false
                binding.edtPassword.error = "Enter your password"
            }
            if (konfirPassword.isEmpty()){
                binding.textInputLayoutKonfirmasiPassword.isPasswordVisibilityToggleEnabled = false
                binding.edtKonfirmasiPassword.error = "Enter your password again, please"
            }
            Toast.makeText(applicationContext, "Complete your data", Toast.LENGTH_SHORT).show()
            showLoading(false)
        }else if (!email.matches(emailPattern.toRegex())){
            showLoading(false)
            binding.edtEmail.error = "Invalid email"
            Toast.makeText(this@RegisterActivity, "Enter your email", Toast.LENGTH_SHORT).show()
        }else if (password.length<8){
            binding.textInputLayoutPassword.isPasswordVisibilityToggleEnabled = false
            showLoading(false)
            binding.edtPassword.error = "Enter a password of at least 8 characters"
            Toast.makeText(this@RegisterActivity, "Enter a password of at least 8 characters", Toast.LENGTH_SHORT).show()
        }else if (password != konfirPassword){
            showLoading(false)
            binding.edtKonfirmasiPassword.error = "Password is not the same, please repeat"
            Toast.makeText(this@RegisterActivity, "Password is not the same, please repeat", Toast.LENGTH_SHORT).show()
        }else {

            viewModel.register(firstName, lastName, email, password)
                .observe(this@RegisterActivity) { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.btnRegister.isEnabled = false
                            showLoading(true)
                        }

                        is Result.Success -> {
                            showLoading(false)
                            binding.btnRegister.isEnabled = true

                            AlertDialog.Builder(this@RegisterActivity).apply {
                                setTitle("Yeay!")
                                setMessage("Register Success, Welcome to Homepage")
                                setPositiveButton("Continue") { _, _ ->
                                    viewModel.login(email, password)
                                        .observe(this@RegisterActivity) { result ->
                                            when (result) {
                                                is Result.Loading -> {
                                                    showLoading(true)
                                                }

                                                is Result.Success -> {
                                                    showLoading(false)
                                                    result.data.let {
                                                        if (it.message == "Login successful") {
                                                            pref.saveAuthToken(it.loginResult.token)
                                                            message(it.message)
                                                            val userId = it.loginResult.userId
                                                            UserSharedPreferences.saveUserId(
                                                                this@RegisterActivity,
                                                                userId
                                                            )
                                                            intent = Intent(
                                                                this@RegisterActivity,
                                                                MainActivity::class.java
                                                            )
                                                            intent.flags =
                                                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                            startActivity(intent)
                                                            finish()
                                                        } else {
                                                            message(it.message.toString())
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
                                create()
                                show()
                            }
                        }

                        is Result.Error -> {
                            binding.btnRegister.isEnabled = true
                            showLoading(false)
                            message(result.error)
                        }
                    }
                }

        }
    }

    private fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}