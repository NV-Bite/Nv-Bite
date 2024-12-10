package com.example.NVBite.ui.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.NVBite.data.repo.AppRepository
class RegisterViewModel(
    private val repository: AppRepository
) : ViewModel() {
    fun registerUser(name: String, email: String, password: String, phoneNumber: String) =
        repository.register(name, email, password, phoneNumber).asLiveData()

    class Build(
        context: Context
    ) : ViewModelProvider.NewInstanceFactory() {
        private val repository = AppRepository(context)

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            RegisterViewModel(repository) as T
    }
}