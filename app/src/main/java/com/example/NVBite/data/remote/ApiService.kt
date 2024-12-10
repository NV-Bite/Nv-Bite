package com.example.NVBite.data.remote

import com.example.NVBite.data.models.DetectImageResponse
import com.example.NVBite.data.models.GetProfileResponse
import com.example.NVBite.data.models.HistoryResponse
import com.example.NVBite.data.models.HistoryResponseItem
import com.example.NVBite.data.models.LoginResponse
import com.example.NVBite.data.models.RegisterResponse
import com.example.NVBite.data.models.UpdateImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("users/signin")
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @POST("users/signup")
    @FormUrlEncoded
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phoneNumber") phoneNumber: String,
    ): RegisterResponse

    @Multipart
    @POST("machine-learning/upload")
    suspend fun detectImage(
        @Part image: MultipartBody.Part,
        @Part("id") id: RequestBody,
    ): DetectImageResponse

    @POST("machine-learning/history")
    @FormUrlEncoded
    suspend fun getHistories(
        @Field("id") id: String
    ): List<HistoryResponseItem>

    @POST("users/getProfile")
    @FormUrlEncoded
    suspend fun getProfileDetail(
        @Field("id") id: String
    ): GetProfileResponse

    @POST("users/updateProfile")
    @FormUrlEncoded
    suspend fun updateImageProfile(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("photoUrl") photoUrl: String,
    ): UpdateImageResponse
}