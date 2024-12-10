package com.example.NVBite.data.repo

import android.content.Context
import com.example.NVBite.data.locals.AccountPreferences
import com.example.NVBite.data.models.GetProfileResponse
import com.example.NVBite.data.remote.ApiConfig
import com.example.NVBite.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class AppRepository(
    context: Context
) {
    private val accountPreferences = AccountPreferences.getInstance(context)
    private val apiService = ApiConfig.getApiService()

    fun login(email: String, password: String) = flow {
        emit(Result.Loading)
        try {
            val loginResponse = apiService.loginUser(email, password)

            runBlocking {
                accountPreferences.saveToken(
                    loginResponse.localId ?: AccountPreferences.preferencesDefaultValue
                )
            }

            emit(Result.Success(loginResponse))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                runBlocking { clearPreferences() }
                emit(Result.Unauthorized("Unauthorized User! Please Login Again"))
            } else {
                emit(Result.Error("Error: ${e.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ) = flow {
        emit(Result.Loading)
        try {
            val registerResponse = apiService.registerUser(name, email, password, phoneNumber)
            emit(Result.Success(registerResponse))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                runBlocking { clearPreferences() }
                emit(Result.Unauthorized("Unauthorized User! Please Login Again"))
            } else {
                emit(Result.Error("Error: ${e.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun detectImage(
        image: File
    ) = flow {
        emit(Result.Loading)
        try {
            val photo = MultipartBody.Part.createFormData(
                "image",
                image.name,
                image.asRequestBody("image/jpeg".toMediaTypeOrNull())
            )

            val id = runBlocking { getToken().first().toString() }
            val idMed = id.toRequestBody("text/plain".toMediaType())

            val uploadResponse = apiService.detectImage(
                photo, idMed
            )

            emit(Result.Success(uploadResponse))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                runBlocking { clearPreferences() }
                emit(Result.Unauthorized("Unauthorized User! Please Login Again"))
            } else {
                emit(Result.Error("Error: ${e.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getHistories() = flow {
        emit(Result.Loading)
        try {
            val id = runBlocking { getToken().first().toString() }
            val historyResponse = apiService.getHistories(id)

            emit(Result.Success(historyResponse))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                runBlocking { clearPreferences() }
                emit(Result.Unauthorized("Unauthorized User! Please Login Again"))
            } else {
                emit(Result.Error("Error: ${e.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getProfileInfo() = flow {
        emit(Result.Loading)
        try {
            val id = runBlocking { getToken().first().toString() }
            val profileResponse = apiService.getProfileDetail(id)

            runBlocking {
                updateProfilePreferences(profileResponse)
            }

            emit(Result.Success(profileResponse))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                runBlocking { clearPreferences() }
                emit(Result.Unauthorized("Unauthorized User! Please Login Again"))
            } else {
                emit(Result.Error("Error: ${e.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun updateImageProfile(
        base64image: String
    ) = flow {
        emit(Result.Loading)
        try {
            val id = runBlocking { getToken().first().toString() }
            val name = runBlocking { getName().first().toString() }
            val imageResponse = apiService.updateImageProfile(id,name, base64image)

            runBlocking {
                updateImageProfilePreferences(imageResponse.photoUrl ?: "")
            }

            emit(Result.Success(imageResponse))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                runBlocking { clearPreferences() }
                emit(Result.Unauthorized("Unauthorized User! Please Login Again"))
            } else {
                emit(Result.Error("Error: ${e.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getToken() = accountPreferences.getToken()

    fun getProfilePic() = accountPreferences.getProfilePic()
    fun getName() = accountPreferences.getName()
    fun getPassword() = accountPreferences.getPassword()
    fun getEmail() = accountPreferences.getEmail()
    fun getPhone() = accountPreferences.getPhone()

    private suspend fun updateImageProfilePreferences(base64image: String) {
        accountPreferences.saveImageProfile(base64image)
    }

    private suspend fun updateProfilePreferences(getProfileResponse: GetProfileResponse) {
        accountPreferences.saveProfileInfo(getProfileResponse)
    }

    suspend fun clearPreferences() {
        accountPreferences.clearPreferences()
    }
}