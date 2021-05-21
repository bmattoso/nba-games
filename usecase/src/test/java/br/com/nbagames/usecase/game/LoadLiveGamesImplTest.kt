package br.com.nbagames.usecase.game

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

@ExperimentalCoroutinesApi
internal class LoadLiveGamesImplTest {

    private lateinit var loadLiveGames: LoadLiveGames

    @BeforeEach
    fun setup() {
        loadLiveGames = LoadLiveGamesImpl()
    }

    @Test
    fun `Run without throwable expected`() {
        assertDoesNotThrow {
            runBlockingTest { loadLiveGames() }
        }
    }
}
