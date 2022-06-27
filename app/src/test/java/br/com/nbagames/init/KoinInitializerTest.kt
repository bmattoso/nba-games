package br.com.nbagames.init

import android.app.Application
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.test.check.checkModules

internal class KoinInitializerTest {

    private lateinit var koinInitializer: KoinInitializer

    @BeforeEach
    fun setup() {
        koinInitializer = KoinInitializer()
    }

    @Test
    fun `Guarantee modules definitions`() {
        val koinApplication = koinInitializer.create(FakeApplication())
        koinApplication.checkModules()
    }

    @Test
    fun `Check Initializer has no dependencies`() {
        assertThat(koinInitializer.dependencies()).isEmpty()
    }

    private class FakeApplication : Application()
}
