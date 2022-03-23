package com.kathayat.simplelogin

import android.content.pm.PackageManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.net.URI.create

    private const val BASE_URL = "http://simple-node-app-nkd.herokuapp.com"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.
    create(moshi)).baseUrl(BASE_URL).build()

    interface ApiService{

    @GET(".")
    fun getAllData(): Call<List<PackageManager.Property>>

}

    object Api {
    val retrofitService: ApiService by lazy{retrofit.create(ApiService::class.java)}
}