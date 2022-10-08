package com.pjurado.curso2223

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

//requireContext().toast("Hola")
fun Context.toast(mensaje: String){
    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(layout: Int, attachToRoot: Boolean = true) =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .into(this)
}