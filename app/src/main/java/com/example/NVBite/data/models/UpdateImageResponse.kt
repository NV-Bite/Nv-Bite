package com.example.NVBite.data.models

import com.google.gson.annotations.SerializedName

data class UpdateImageResponse(

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
