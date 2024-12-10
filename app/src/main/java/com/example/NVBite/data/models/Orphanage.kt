package com.example.NVBite.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Orphanage(
    val name: String,
    val address: String,
    val phone: String,
    val mapUrl: String
) : Parcelable
