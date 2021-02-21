package com.example.koinsample.core.interceptors

import android.content.Context
import com.example.koinsample.R
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(private val context: Context) : Interceptor {

    companion object {
        private const val CLIENT_ID = "Client-ID"
        private const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader(CLIENT_ID, context.getString(R.string.key))
            .addHeader(AUTHORIZATION,"Bearer ${context.getString(R.string.access_token)}")
            .build()


        return chain.proceed(request)
    }

}