package br.com.nbagames.remote.game

import br.com.nbagames.model.Quarter
import br.com.nbagames.remote.game.fake.FakeGameService
import br.com.nbagames.remote.game.fixture.GameListResponseFixture
import br.com.nbagames.remote.game.mapper.GameMapper
import br.com.nbagames.remote.team.mapper.TeamMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerializationException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.text.DecimalFormat
import java.util.Calendar

@ExperimentalCoroutinesApi
internal class GameRemoteImplTest {

    private lateinit var gameRemote: GameRemote
    private lateinit var gameService: FakeGameService

    @BeforeEach
    fun setup() {
        gameService = FakeGameService()

        gameRemote = GameRemoteImpl(
            gameMapper = GameMapper(TeamMapper()),
            gameService = gameService
        )
    }

    @Test
    fun `When failed parse exception just throw an Exception to upper layer`() {
        gameService.throwException = true

        assertThrows<SerializationException> {
            runBlocking { gameRemote.getLiveGameList() }
        }
    }

    @Test
    fun `Request live game list from remote with today date`() {
        val twoDigitsFormat = DecimalFormat("00")
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth = twoDigitsFormat.format(Calendar.getInstance().get(Calendar.MONTH) + 1)
        val currentDay = twoDigitsFormat.format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))

        runBlocking { gameRemote.getLiveGameList() }

        assertThat(gameService.requestedDate).matches("2\\d\\d\\d-[0-1]\\d-[0-3]\\d") // 0000-00-00 date pattern
        assertThat(gameService.requestedDate).isEqualTo("$currentYear-$currentMonth-$currentDay")
    }

    @Test
    fun `When gameService return empty list then return empty list`() {
        gameService.gameListResponse = GameListResponseFixture.getEmptyList()

        runBlocking {
            assertThat(gameRemote.getLiveGameList()).isEmpty()
        }
    }

    @Test
    fun `When get live game list from response parse into game model list`() {
        gameService.gameListResponse = GameListResponseFixture.get()

        runBlocking {
            val parsedGameList = gameRemote.getLiveGameList()

            assertThat(parsedGameList).isNotEmpty()
            parsedGameList.onEachIndexed { index, game ->
                val correlatedGame = gameService.gameListResponse.gameList[index]
                assertThat(game.id).isEqualTo(correlatedGame.id)
                assertThat(game.currentClock).isNotNull()
                assertThat(game.currentClock).isEqualTo(correlatedGame.status.clock)
                assertThat(game.homePoints).isEqualTo(correlatedGame.scores.home.points)
                assertThat(game.visitorPoints).isEqualTo(correlatedGame.scores.visitors.points)
                assertThat(game.quarter).isEqualTo(Quarter.Third)
            }
        }
    }
}