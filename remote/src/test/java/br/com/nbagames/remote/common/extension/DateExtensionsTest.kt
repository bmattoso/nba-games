package br.com.nbagames.remote.common.extension

import com.google.common.truth.Truth.assertThat
import java.util.Date
import org.junit.jupiter.api.Test

class DateExtensionsTest {

    @Test
    fun `Convert date to API pattern`() {
        val date = Date(1656338416166) // Mon Jun 27 2022 14:00:16
        assertThat(date.formatToRequest()).isEqualTo("2022-06-27")
    }
}
