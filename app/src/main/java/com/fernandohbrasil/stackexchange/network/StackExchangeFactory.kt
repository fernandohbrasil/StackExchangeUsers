package com.fernandohbrasil.stackexchange.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://gateway.marvel.com/"

class StackExchangeFactory {

    fun marvelApi(): StackExchangeApi {
        val requestInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl: HttpUrl = originalRequest.url

            val url = originalHttpUrl.newBuilder()
                //.addQueryParameter("apikey", PUBLIC_KEY)
                .build()

            val requestBuilder: Request.Builder = originalRequest.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val debugInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(debugInterceptor)
        builder.addInterceptor(requestInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(builder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StackExchangeApi::class.java)
    }
}