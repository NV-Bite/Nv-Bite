package com.example.NVBite.ui.home.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.NVBite.data.repo.AppRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileViewModel(
    private val repository: AppRepository
) : ViewModel() {
    val getName = runBlocking { repository.getName().first().toString() }
    val getPhone = runBlocking { repository.getPhone().first().toString() }
    val getPassword = runBlocking { repository.getPassword().first().toString() }
    val getEmail = runBlocking { repository.getEmail().first().toString() }

    fun getProfilePic() = repository.getProfilePic().asLiveData()
    fun updateProfilePic(base64image: String) = repository.updateImageProfile(base64image).asLiveData()

    fun clearPreferences() {
        viewModelScope.launch {
            repository.clearPreferences()
        }
    }

    class Build(
        context: Context
    ) : ViewModelProvider.NewInstanceFactory() {
        private val repository = AppRepository(context)

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProfileViewModel(repository) as T
    }
}