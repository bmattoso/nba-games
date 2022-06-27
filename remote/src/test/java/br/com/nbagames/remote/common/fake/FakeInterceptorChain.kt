package br.com.nbagames.remote.common.fake

import java.util.concurrent.TimeUnit
import okhttp3.Call
import okhttp3.Connection
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response

const val BASE_URL = "https://api-nba-v1.p.rapidapi.com/"

class FakeInterceptorChain : Interceptor.Chain {

    var modifiedRequest: Request? = null
    var initialRequest: Request.Builder = Request.Builder().url(BASE_URL)

    override fun call(): Call {
        TODO("Not yet implemented")
    }

    override fun connectTimeoutMillis(): Int {
        TODO("Not yet implemented")
    }

    override fun connection(): Connection? {
        TODO("Not yet implemented")
    }

    override fun proceed(request: Request): Response {
        modifiedRequest = request

        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_2)
            .message("Fake response message")
            .build()
    }

    override fun readTimeoutMillis(): Int {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        return initialRequest.build()
    }

    override fun withConnectTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
        TODO("Not yet implemented")
    }

    override fun withReadTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
        TODO("Not yet implemented")
    }

    override fun withWriteTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain {
        TODO("Not yet implemented")
    }

    override fun writeTimeoutMillis(): Int {
        TODO("Not yet implemented")
    }
}
