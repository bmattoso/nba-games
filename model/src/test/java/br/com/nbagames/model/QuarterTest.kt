package br.com.nbagames.model

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class QuarterTest {

    @Test
    fun `Get string description of first quarter`() {
        assertThat(Quarter.First.descriptionResId).isEqualTo(R.string.first_quarter)
    }

    @Test
    fun `Get string description of second quarter`() {
        assertThat(Quarter.Second.descriptionResId).isEqualTo(R.string.second_quarter)
    }

    @Test
    fun `Get string description of third quarter`() {
        assertThat(Quarter.Third.descriptionResId).isEqualTo(R.string.third_quarter)
    }

    @Test
    fun `Get string description of fourth quarter`() {
        assertThat(Quarter.Fourth.descriptionResId).isEqualTo(R.string.fourth_quarter)
    }
}
