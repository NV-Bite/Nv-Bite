package com.example.NVBite.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.NVBite.data.repo.AppRepository

class LoginViewModel(
    private val repository: AppRepository
) : ViewModel() {
    fun loginUser(email: String, password: String) =
        repository.login(email, password).asLiveData()

    class Build(
        context: Context
    ) : ViewModelProvider.NewInstanceFactory() {
        private val repository = AppRepository(context)

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            LoginViewModel(repository) as T
    }
}