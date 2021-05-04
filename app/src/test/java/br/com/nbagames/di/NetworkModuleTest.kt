package br.com.nbagames.di

import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Before
import org.junit.Test
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.test.check.checkModules

class NetworkModuleTest {

    private lateinit var koinApplication: KoinApplication

    @ExperimentalSerializationApi
    @Before
    fun setup() {
        koinApplication = startKoin {
            modules(networkModule)
        }
    }

    @Test
    fun `Should create all dependencies from networkModule`() {
        koinApplication.checkModules()
    }
}