package com.example.NVBite.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.NVBite.utils.Result
import androidx.appcompat.app.AppCompatActivity
import com.example.NVBite.ui.register.RegisterActivity
import com.example.NVBite.databinding.ActivityLoginBinding
import com.example.NVBite.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        LoginViewModel.Build(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            loginButton.setOnClickListener {
                if (isValid()) {
                    loginViewModel.loginUser(email.text.toString(), password.text.toString())
                        .observe(this@LoginActivity) { result ->
                            when (result) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }

                                is Result.Success -> {
                                    showLoading(false)
                                    finishAffinity()
                                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                }

                                is Result.Error -> {
                                    showLoading(false)
                                    showToast(result.error)
                                }

                                is Result.Unauthorized -> {
                                    showLoading(false)
                                    showToast(result.error)
                                }
                            }
                        }
                }
            }

            btnSignup.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun isValid() = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.email.text).matches() || binding.email.text.isNullOrEmpty()) {
        showToast("Fill email correctly!")
        false
    } else if (binding.password.text.length in 0..7 || binding.password.text.isNullOrEmpty()) {
        showToast("Password must be at least 8 characters")
        false
    } else {
        true
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            loginButton.isEnabled = !isLoading
            btnSignup.isEnabled = !isLoading
            email.isEnabled = !isLoading
            password.isEnabled = !isLoading
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}