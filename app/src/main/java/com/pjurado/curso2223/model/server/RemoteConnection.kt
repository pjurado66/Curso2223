package com.pjurado.curso2223.model.server

import com.google.gson.Gson
import com.pjurado.curso2223.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RemoteConnection {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RemoteService = builder.create()
}