package br.com.nbagames.di

import br.com.nbagames.BuildConfig
import br.com.nbagames.core.network.AuthorizationInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

private const val APPLICATION_JSON = "application/json"

@ExperimentalSerializationApi
val networkModule = module {

    factory { AuthorizationInterceptor() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthorizationInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_BASE_URL)
            .addConverterFactory(Json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .client(get())
            .build()
    }
}
