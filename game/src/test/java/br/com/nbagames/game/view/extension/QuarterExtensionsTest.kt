package br.com.nbagames.game.view.extension

import br.com.nbagames.game.R
import br.com.nbagames.model.Quarter
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class QuarterExtensionsTest {

    @Test
    fun `Get string description of first quarter`() {
        assertThat(Quarter.First.getResourceTextId()).isEqualTo(R.string.first_quarter)
    }

    @Test
    fun `Get string description of second quarter`() {
        assertThat(Quarter.Second.getResourceTextId()).isEqualTo(R.string.second_quarter)
    }

    @Test
    fun `Get string description of third quarter`() {
        assertThat(Quarter.Third.getResourceTextId()).isEqualTo(R.string.third_quarter)
    }

    @Test
    fun `Get string description of fourth quarter`() {
        assertThat(Quarter.Fourth.getResourceTextId()).isEqualTo(R.string.fourth_quarter)
    }
}
