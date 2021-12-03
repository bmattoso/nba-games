package br.com.nbagames.repository.game

import br.com.nbagames.model.Game
import br.com.nbagames.remote.game.GameRemote

class GameRepositoryImpl(
    private val gameRemote: GameRemote
) : GameRepository {

    override suspend fun getLiveGamesFromRemote(): List<Game> {
        return gameRemote.getLiveGameList()
    }

    override suspend fun getLiveGamesFromCache(): List<Game> {
        return emptyList()
    }
}
