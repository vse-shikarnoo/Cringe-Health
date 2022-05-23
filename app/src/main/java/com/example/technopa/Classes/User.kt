package com.example.technopa.Classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val name: String? = "",
    val surname: String? = "",
    val height: Double? = 0.0,
    val weight: Double? = 0.0,
    // val statistic:Statistic,
    // val Achievements:List<Achievement>
) : Parcelable