package br.com.nbagames.remote.common.extension

import br.com.nbagames.model.Quarter
import br.com.nbagames.model.toQuarter
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class QuarterExtensionsTest {

    @Test
    fun `When receive 1 from API parse to first quarter`() {
        assertThat(1.toQuarter()).isEqualTo(Quarter.First)
    }

    @Test
    fun `When receive 2 from API parse to second quarter`() {
        assertThat(2.toQuarter()).isEqualTo(Quarter.Second)
    }

    @Test
    fun `When receive 3 from API parse to third quarter`() {
        assertThat(3.toQuarter()).isEqualTo(Quarter.Third)
    }

    @Test
    fun `When receive 4 from API parse to fourth quarter`() {
        assertThat(4.toQuarter()).isEqualTo(Quarter.Fourth)
    }

    @Test
    fun `When receive any number out of range 1-4 return first quarter`() {
        assertThat(Random.nextInt(5, 100).toQuarter()).isEqualTo(Quarter.First)
    }
}
