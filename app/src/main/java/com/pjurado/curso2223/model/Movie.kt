package com.pjurado.curso2223.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String = "",
    val urlImagen: String = "",
    val description: String = "",
    val original_languague: String = "",
    val title_original: String = "",
    val valoracion: Double = 0.0
): Parcelable {
}