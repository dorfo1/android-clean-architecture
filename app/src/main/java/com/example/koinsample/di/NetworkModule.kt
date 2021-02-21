package com.example.koinsample.di

import DataUnwrapInterceptor
import com.example.koinsample.BuildConfig
import com.example.koinsample.core.interceptors.AuthenticationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "https://api.twitch.tv/helix/"
    private const val AUTH_INTERCEPTOR = "AUTH_INTERCEPTOR"
    private const val DATA_UNWRAP_INTERCEPTOR = "DATA_UNWRAP_INTERCEPTOR"

    val dependencyModules = module {
        single<Retrofit> { getRetrofit(get()) }
        single<OkHttpClient> { getOkHttpCliente(get(named(AUTH_INTERCEPTOR)), get(named(DATA_UNWRAP_INTERCEPTOR))) }


        factory(named(AUTH_INTERCEPTOR)) { AuthenticationInterceptor(get()) }
        factory(named(DATA_UNWRAP_INTERCEPTOR)) { DataUnwrapInterceptor() }
    }

    private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getOkHttpCliente(
            authenticationInterceptor: AuthenticationInterceptor,
            dataUnwrapInterceptor: DataUnwrapInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor(authenticationInterceptor)
        if (BuildConfig.DEBUG) okHttpClient.addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })


        return okHttpClient.build()
    }
}