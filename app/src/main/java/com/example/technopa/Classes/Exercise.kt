package com.example.technopa.Classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exercise(
    val title: String = "",
    val povtoreniya: String? = null,
    val opisanie: String = ""
) : Parcelable