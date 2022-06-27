package br.com.nbagames.remote.game

import br.com.nbagames.model.Game
import br.com.nbagames.remote.common.extension.formatToRequest
import br.com.nbagames.remote.game.mapper.GameMapper
import br.com.nbagames.remote.game.service.GameService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.util.Date

class GameRemoteImpl(
    private val gameService: GameService,
    private val gameMapper: GameMapper
) : GameRemote {

    override suspend fun getLiveGameList(): List<Game> {
        return withContext(IO) {
            val today = Date()
            val liveGameResponse = gameService.getGamesFromDate(today.formatToRequest())
            gameMapper.mapLiveGameList(liveGameResponse.gameList)
        }
    }
}
