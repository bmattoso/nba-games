package br.com.nbagames.remote.di

import br.com.nbagames.remote.common.AuthorizationInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private const val APPLICATION_JSON = "application/json"

object NetworkModule {

    @ExperimentalSerializationApi
    fun getNetworkModule(baseUrl: String, apiKey: String, apiHost: String): Module {
        return module {
            factory { AuthorizationInterceptor(apiKey = apiKey, apiHost = apiHost) }

            single {
                OkHttpClient.Builder()
                    .addInterceptor(get<AuthorizationInterceptor>())
                    .build()
            }

            single {
                val jsonConfiguration = Json {
                    ignoreUnknownKeys = true
                }
                Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(jsonConfiguration.asConverterFactory(APPLICATION_JSON.toMediaType()))
                    .client(get())
                    .build()
            }
        }
    }
}
