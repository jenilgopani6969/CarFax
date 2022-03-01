package com.worldimage.carfax.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.worldimage.carfax.data.response.VehicleListResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://carfax-for-consumers.firebaseio.com/assignment.json

interface ApiService {

    @GET("assignment.json")
    fun getCarListing(): Deferred<VehicleListResponse>

    companion object {
        operator fun invoke(): ApiService {
            val requestInterceptor = Interceptor {chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://carfax-for-consumers.firebaseio.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}