package br.com.nbagames.core.extension

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.junit5.AutoCloseKoinTest
import java.util.Date
import java.util.Locale

class DateExtensionsTest : AutoCloseKoinTest() {

    @BeforeEach
    fun setup() {
        startKoin {
            modules(module { factory<Locale> { Locale.CANADA } })
        }
    }

    @Test
    fun `Convert date to API pattern`() {
        val date = Date(1656338416166) // Mon Jun 27 2022 14:00:16
        assertThat(date.formatToRequest()).isEqualTo("2022-06-27")
    }
}
