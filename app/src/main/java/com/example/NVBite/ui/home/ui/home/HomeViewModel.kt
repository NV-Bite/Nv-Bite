package com.example.NVBite.ui.home.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.NVBite.data.repo.AppRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeViewModel(private val repository: AppRepository) :
    ViewModel() {
    val getName = runBlocking { repository.getName().first().toString() }

    fun getProfileInfo() = repository.getProfileInfo().asLiveData()

    class Build(
        context: Context
    ) : ViewModelProvider.NewInstanceFactory() {
        private val repository = AppRepository(context)

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(repository) as T
    }
}