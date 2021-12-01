package br.com.nbagames.remote.common

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val X_RAPID_API_KEY = "x-rapidapi-key"
private const val X_RAPID_API_HOST = "x-rapidapi-host"

class AuthorizationInterceptor(
    private val apiKey: String,
    private val apiHost: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authenticatedRequest = authenticateRequest(chain.request())
        return chain.proceed(authenticatedRequest)
    }

    private fun authenticateRequest(request: Request): Request {
        return request.newBuilder()
            .addHeader(X_RAPID_API_KEY, apiKey)
            .addHeader(X_RAPID_API_HOST, apiHost)
            .build()
    }
}
