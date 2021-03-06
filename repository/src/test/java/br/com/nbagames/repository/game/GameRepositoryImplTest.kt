package br.com.nbagames.repository.game

import br.com.nbagames.repository.game.fake.FakeGameRemote
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class GameRepositoryImplTest {

    private lateinit var gameRepository: GameRepository
    private val gameRemote: FakeGameRemote = FakeGameRemote()

    @BeforeEach
    fun setup() {
        gameRepository = GameRepositoryImpl(
            gameRemote = gameRemote
        )
    }

    @Test
    fun `Get live games from game remote with empty list and return without modification`() = runTest {
        val gameList = gameRepository.getLiveGamesFromRemote()
        assertThat(gameList).isEmpty()
    }

    @Test
    fun `Get live games from game remote with filled list and return without modification`() = runTest {
        val expectedGameList = gameRemote.populateGameList()

        val gameList = gameRepository.getLiveGamesFromRemote()
        assertThat(gameList).isNotEmpty()
        assertThat(gameList).isEqualTo(expectedGameList)
    }

    @Test
    fun `When getting live games from cache return empty list its not been implemented`() = runTest {
        val gameList = gameRepository.getLiveGamesFromCache()
        assertThat(gameList).isEmpty()
    }
}
