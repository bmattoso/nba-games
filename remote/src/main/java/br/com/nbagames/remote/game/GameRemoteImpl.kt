package br.com.nbagames.remote.game

import br.com.nbagames.model.Game
import br.com.nbagames.remote.game.mapper.GameMapper
import br.com.nbagames.remote.game.service.GameService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GameRemoteImpl(
    private val gameService: GameService,
    private val gameMapper: GameMapper
) : GameRemote {

    override suspend fun getLiveGameList(): List<Game> {
        return withContext(IO) {
            val liveGameResponse = gameService.getLiveGamesNow()
            gameMapper.mapLiveGameList(liveGameResponse.liveGameApiResponse.liveGameList)
        }
    }
}
