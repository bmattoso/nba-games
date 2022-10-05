package br.com.nbagames.remote.game.fake

import br.com.nbagames.remote.game.fixture.GameListResponseFixture
import br.com.nbagames.remote.game.response.GameListResponse
import br.com.nbagames.remote.game.service.GameService
import br.com.nbagames.remote.statistics.GameStatisticsResponse
import kotlinx.serialization.SerializationException

class FakeGameService : GameService {

    var throwException = false
    var gameListResponse: GameListResponse = GameListResponseFixture.get()
    var requestedDate: String = ""

    override suspend fun getGamesFromDate(date: String): GameListResponse {
        requestedDate = date

        if (throwException) {
            throw SerializationException()
        }

        return gameListResponse
    }

    override suspend fun getGameDetail(gameId: Int): GameListResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getGameStatistics(gameId: Int): GameStatisticsResponse {
        TODO("Not yet implemented")
    }
}
