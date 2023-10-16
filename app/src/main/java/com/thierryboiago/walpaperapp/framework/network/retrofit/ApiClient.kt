package com.thierryboiago.walpaperapp.framework.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiClient<T>(url: String, okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    fun createApi(apiInterface: Class<T>): T = retrofit.create(apiInterface)
}