package br.com.nbagames.usecase.game

import br.com.nbagames.repository.game.GameRepository
import br.com.nbagames.usecase.game.fake.FakeGameRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

@ExperimentalCoroutinesApi
internal class LoadLiveGamesImplTest {

    private lateinit var loadLiveGames: LoadLiveGames

    @Test
    fun `Run without throwable expected`() {
        instantiateLoadLiveGamesUseCase(FakeGameRepository())

        assertDoesNotThrow {
            runBlockingTest { loadLiveGames() }
        }
    }

    @Test
    fun `Receive game list into flow`() {
        val fakeGameRepository = FakeGameRepository()
        val expectedGameList = fakeGameRepository.populateGameList()
        instantiateLoadLiveGamesUseCase(fakeGameRepository)

        runBlockingTest {
            val gameListFlow = loadLiveGames()
            gameListFlow.collect { gameList ->
                assertThat(gameList).isEqualTo(expectedGameList)
            }
        }
    }

    @Test
    fun `Receive empty game list into flow`() {
        val fakeGameRepository = FakeGameRepository().apply { gameList.clear() }
        instantiateLoadLiveGamesUseCase(fakeGameRepository)

        runBlockingTest {
            val gameListFlow = loadLiveGames()
            gameListFlow.collect { gameList ->
                assertThat(gameList).isEmpty()
            }
        }
    }

    private fun instantiateLoadLiveGamesUseCase(gameRepository: GameRepository) {
        loadLiveGames = LoadLiveGamesImpl(
            gameRepository = gameRepository
        )
    }
}
