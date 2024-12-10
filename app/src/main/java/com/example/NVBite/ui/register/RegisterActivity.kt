package com.example.NVBite.ui.register

import com.example.NVBite.utils.Result
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.NVBite.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val registerViewModel by viewModels<RegisterViewModel> {
        RegisterViewModel.Build(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            registerButton.setOnClickListener {
                if (isValid()) {
                    registerViewModel.registerUser(
                        name.text.toString(),
                        email.text.toString(),
                        password.text.toString(),
                        phone.text.toString(),
                    )
                        .observe(this@RegisterActivity) { result ->
                            when (result) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }

                                is Result.Success -> {
                                    showLoading(false)
                                    showToast("Register Success! Please Log In")
                                    finish()
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

            btnSignIn.setOnClickListener {
                finish()
            }
        }
    }


    private fun isValid() = if (binding.name.text.isNullOrEmpty()) {
        showToast("Name cannot be empty")
        false
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.email.text).matches() || binding.email.text.isNullOrEmpty()) {
        showToast("Fill email correctly")
        false
    } else if (binding.phone.text.isNullOrEmpty()) {
        showToast("Phone cannot be empty")
        false
    } else if (binding.password.text.length in 0..7 || binding.password.text.isNullOrEmpty()) {
        showToast("Password must be at least 8 characters")
        false
    } else {
        true
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            registerButton.isEnabled = !isLoading
            btnSignIn.isEnabled = !isLoading
            name.isEnabled = !isLoading
            email.isEnabled = !isLoading
            password.isEnabled = !isLoading
            phone.isEnabled = !isLoading
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}