package com.sevenpeakssoftware.patipan.shared.remote

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptors : Interceptor {
    private val accept = "Accept"
    private val contentType = "Content-Type"
    private val acceptValue = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val request = oldRequest.newBuilder().apply {
            addHeader(accept, acceptValue)
            addHeader(contentType, acceptValue)
        }.build()

        return chain.proceed(request)
    }
}