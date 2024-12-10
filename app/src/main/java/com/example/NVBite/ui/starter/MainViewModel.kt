package com.example.NVBite.ui.starter

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.NVBite.data.repo.AppRepository

class MainViewModel(
    private val repository: AppRepository
) : ViewModel() {
    fun getToken() = repository.getToken().asLiveData()

    class Build(
        context: Context
    ) : ViewModelProvider.NewInstanceFactory() {
        private val repository = AppRepository(context)

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(repository) as T
    }
}