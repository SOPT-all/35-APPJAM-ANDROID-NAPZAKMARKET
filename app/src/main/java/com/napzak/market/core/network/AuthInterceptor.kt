package com.napzak.market.core.network

import com.napzak.market.core.local.TokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val authRequest = runBlocking {
            val token = tokenDataStore.getAccessToken()

            Timber.tag("AuthInterceptor").d("ACCESS TOKEN: $token")

            if (token?.isNotBlank() == true) {
                originalRequest.newBuilder().newAuthBuilder().build()
            } else {
                originalRequest
            }
        }

        val response = chain.proceed(authRequest)

        return response
    }

    private suspend fun Request.Builder.newAuthBuilder() =
        this.addHeader(AUTHORIZATION, "$BEARER ${tokenDataStore.getAccessToken()}")

    companion object {
        private const val BEARER = "Bearer"
        private const val AUTHORIZATION = "Authorization"
    }
}