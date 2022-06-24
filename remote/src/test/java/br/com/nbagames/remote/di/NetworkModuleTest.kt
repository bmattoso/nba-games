package br.com.nbagames.remote.di

import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.test.check.checkModules

class NetworkModuleTest {

    private lateinit var koinApplication: KoinApplication

    @ExperimentalSerializationApi
    @BeforeEach
    fun setup() {
        koinApplication = startKoin {
            modules(NetworkModule.getNetworkModule("https://api-nba-v1.p.rapidapi.com/", "", ""))
        }
    }

    @Test
    fun `Should create all dependencies from networkModule`() {
        koinApplication.checkModules()
    }
}
