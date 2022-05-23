package com.example.technopa.Classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Eda(
    val title: String? = null,
    val kalorii: Double? = null,
    val belki: Double? = null,
    val zhiri: Double? = null,
    val uglevodi: Double? = null
) : Parcelable

