package com.example.NVBite.data.models

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("HistoryResponse")
	val historyResponse: List<HistoryResponseItem> = mutableListOf()
)

data class HistoryResponseItem(

	@field:SerializedName("GenText")
	val genText: String? = null,

	@field:SerializedName("predicted_class")
	val predictedClass: String? = null,

	@field:SerializedName("confidence")
	val confidence: Any? = null,

	@field:SerializedName("predict_time")
	val predictTime: Any? = null
)
