package com.example.technopa.Classes

import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dieta(
    var title: String? = "",
    var id: Long? = null,
    var priemPishiList: List<PriemPishi>? = emptyList(),
    var kaloriipd: Double? = 0.0, // Каллории и БЖУ в день
    var opisanie: String? = ""
) : Parcelable {
    override fun toString(): String {
        return "Dieta[title = $title;priem pishi = $priemPishiList]"
    }
}
