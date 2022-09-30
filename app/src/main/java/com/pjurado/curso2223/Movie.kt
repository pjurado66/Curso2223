package com.pjurado.curso2223

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie(val title: String, val urlImagen: String): Parcelable {
}