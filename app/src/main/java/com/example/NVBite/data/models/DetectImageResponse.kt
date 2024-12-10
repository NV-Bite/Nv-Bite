package com.example.NVBite.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectImageResponse(

	@field:SerializedName("GenText")
	val genText: String? = null,

	@field:SerializedName("predicted_class")
	val predictedClass: String? = null,

	@field:SerializedName("confidence")
	val confidence: Double? = null,

	@field:SerializedName("predict_time")
	val predictTime: Double? = null
) : Parcelable
