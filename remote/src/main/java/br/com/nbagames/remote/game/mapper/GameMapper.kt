package br.com.nbagames.remote.game.mapper

import br.com.nbagames.model.Game
import br.com.nbagames.remote.game.response.LiveGameResponse

class GameMapper {
    fun mapLiveGameList(liveGameResponse: LiveGameResponse): List<Game> {
        return liveGameResponse.liveGameList.map { gameResponse ->
            Game(
                id = gameResponse.id
            )
        }
    }
}
