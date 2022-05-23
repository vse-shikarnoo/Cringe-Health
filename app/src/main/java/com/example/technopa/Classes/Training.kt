package com.example.technopa.Classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Training(
    var id: Long? = null,
    val title: String? = ",",
    val exercises: List<Exercise> = emptyList(),
    val kalorii: Double = 0.0,
    val time: Int = 0,
    val opisanie: String? = ""
) : Parcelable