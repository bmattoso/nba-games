package br.com.nbagames.remote.game

import br.com.nbagames.model.Game
import br.com.nbagames.model.statistics.GameStatistics
import br.com.nbagames.remote.common.extension.formatToRequest
import br.com.nbagames.remote.game.mapper.GameMapper
import br.com.nbagames.remote.game.mapper.GameStatisticsMapper
import br.com.nbagames.remote.game.service.GameService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.util.Date

class GameRemoteImpl(
    private val gameService: GameService,
    private val gameMapper: GameMapper,
    private val gameStatisticsMapper: GameStatisticsMapper
) : GameRemote {

    override suspend fun getLiveGameList(): List<Game> {
        return withContext(IO) {
            val today = Date()
            val liveGameResponse = gameService.getGamesFromDate(today.formatToRequest())
            gameMapper.mapLiveGameList(liveGameResponse.gameList)
        }
    }

    override suspend fun getGameDetail(gameId: Int): Game {
        return withContext(IO) {
            val gameResponse = gameService.getGameDetail(gameId)

            if (gameResponse.gameList.size > 1) {
                throw MultipleGamesException()
            }

            gameMapper.mapGame(gameResponse.gameList[0])
        }
    }

    override suspend fun getGameStatistics(gameId: Int): GameStatistics {
        return withContext(IO) {
            val gameStatisticsResponse = gameService.getGameStatistics(gameId)
            gameStatisticsMapper.mapGameStatistics(gameStatisticsResponse)
        }
    }
}
