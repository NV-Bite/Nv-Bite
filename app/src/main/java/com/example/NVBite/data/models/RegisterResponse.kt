package com.example.NVBite.data.models

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("expiresIn")
	val expiresIn: String? = null,

	@field:SerializedName("idToken")
	val idToken: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("localId")
	val localId: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)
