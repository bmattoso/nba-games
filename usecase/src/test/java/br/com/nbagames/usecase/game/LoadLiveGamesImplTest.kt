package br.com.nbagames.usecase.game

import br.com.nbagames.repository.game.GameRepository
import br.com.nbagames.usecase.game.fake.FakeGameRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.fail

@ExperimentalCoroutinesApi
internal class LoadLiveGamesImplTest {

    private lateinit var loadLiveGames: LoadLiveGames

    @Test
    fun `Run without throwable expected`() {
        instantiateLoadLiveGamesUseCase(FakeGameRepository())

        assertDoesNotThrow {
            runTest { loadLiveGames() }
        }
    }

    @Test
    fun `Receive game list into flow`() = runTest {
        val fakeGameRepository = FakeGameRepository()
        val expectedGameList = fakeGameRepository.populateGameList()
        instantiateLoadLiveGamesUseCase(fakeGameRepository)

        val gameListFlow = loadLiveGames()
        gameListFlow.onEach { gameList ->
            assertThat(gameList).isEqualTo(expectedGameList)
        }
    }

    @Test
    fun `Receive empty game list into flow`() = runTest {
        val fakeGameRepository = FakeGameRepository().apply { gameList.clear() }
        instantiateLoadLiveGamesUseCase(fakeGameRepository)

        val gameListFlow = loadLiveGames()
        gameListFlow.onEach { gameList ->
            assertThat(gameList).isEmpty()
        }
    }

    @Test
    fun `Catch and throw exception from any dependencies`() = runTest {
        val fakeGameRepository = FakeGameRepository().apply { throwExceptionRemote = RuntimeException() }
        instantiateLoadLiveGamesUseCase(fakeGameRepository)

        loadLiveGames().catch { exception ->
            assertThat(exception).isInstanceOf(RuntimeException::class.java)
        }.onEach {
            fail("Should not receive any model")
        }.launchIn(this)
    }

    private fun instantiateLoadLiveGamesUseCase(gameRepository: GameRepository) {
        loadLiveGames = LoadLiveGamesImpl(
            gameRepository = gameRepository
        )
    }
}
