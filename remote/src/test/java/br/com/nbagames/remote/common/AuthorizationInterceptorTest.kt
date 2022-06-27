package br.com.nbagames.remote.common

import br.com.nbagames.remote.common.fake.FakeInterceptorChain
import com.google.common.truth.Truth.assertThat
import java.util.UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AuthorizationInterceptorTest {

    private lateinit var fakeInterceptorChain: FakeInterceptorChain

    @BeforeEach
    fun setup() {
        fakeInterceptorChain = FakeInterceptorChain()
    }

    @Test
    fun `Intercept request without any authorization header`() {
        val authorizationInterceptor = AuthorizationInterceptor(apiKey = "", apiHost = "")

        val requestWithoutAuthorizationHeader = fakeInterceptorChain.initialRequest.build()
        assertThat(requestWithoutAuthorizationHeader.header("x-rapidapi-key")).isNull()
        assertThat(requestWithoutAuthorizationHeader.header("x-rapidapi-host")).isNull()

        authorizationInterceptor.intercept(fakeInterceptorChain)
    }

    @Test
    fun `Add authorization header to any request intercepted`() {
        val randomApiKey = UUID.randomUUID().toString()
        val randomApiHost = UUID.randomUUID().toString()
        val authorizationInterceptor = AuthorizationInterceptor(apiKey = randomApiKey, apiHost = randomApiHost)

        authorizationInterceptor.intercept(fakeInterceptorChain)

        val authenticatedRequest = fakeInterceptorChain.modifiedRequest
        assertThat(authenticatedRequest).isNotNull()
        assertThat(authenticatedRequest?.header("x-rapidapi-host")).isEqualTo(randomApiHost)
        assertThat(authenticatedRequest?.header("x-rapidapi-key")).isEqualTo(randomApiKey)
    }
}
