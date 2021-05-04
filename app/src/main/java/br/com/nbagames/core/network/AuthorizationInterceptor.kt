package br.com.nbagames.core.network

import br.com.nbagames.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val X_RAPID_API_KEY = "x-rapidapi-key"
private const val X_RAPID_API_HOST = "x-rapidapi-host"

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authenticatedRequest = authenticateRequest(chain.request())
        return chain.proceed(authenticatedRequest)
    }

    private fun authenticateRequest(request: Request): Request {
        return request.newBuilder()
            .addHeader(X_RAPID_API_KEY, BuildConfig.X_RAPID_API_KEY)
            .addHeader(X_RAPID_API_HOST, BuildConfig.X_RAPID_API_HOST)
            .build()
    }
}
