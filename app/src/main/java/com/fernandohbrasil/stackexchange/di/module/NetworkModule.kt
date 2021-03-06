package com.fernandohbrasil.stackexchange.di.module

import com.fernandohbrasil.stackexchange.network.StackExchangeApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.stackexchange.com"
private const val KEY = "key"
private const val KEY_VALUE = "Fn1d3)FRZdHVG4anQNQK)Q(("

@Module(includes = [ViewModelModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun stackExchangeApi(): StackExchangeApi {
        val requestInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl: HttpUrl = originalRequest.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(KEY, KEY_VALUE)
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