package com.example.technopa.Classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PriemPishi(
    val title: String? = null,
    val eda: List<Eda>? = null,
    var kaloriiO: Double? = 0.0, // Каллории и БЖУ общее за прием пищи
    var belkiO: Double? = 0.0,
    var zhiriO: Double? = 0.0,
    var uglevodiO: Double? = 0.0
) : Parcelable
