package com.example.NVBite.ui.home.ui.camera

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.NVBite.data.repo.AppRepository
import java.io.File

class CameraViewModel(private val repository: AppRepository) :
    ViewModel() {
    fun detectImage(
        file: File,
    ) = repository.detectImage(
        file
    ).asLiveData()

    class Build(
        context: Context
    ) : ViewModelProvider.NewInstanceFactory() {
        private val repository = AppRepository(context)

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CameraViewModel(repository) as T
    }
}