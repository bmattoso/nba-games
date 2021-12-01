package br.com.nbagames.repository.game

import br.com.nbagames.model.LiveGame

class GameRepositoryImpl : GameRepository {

    override suspend fun getLiveGamesFromRemote(): List<LiveGame> {
        return emptyList()
    }

    override suspend fun getLiveGamesFromCache(): List<LiveGame> {
        return emptyList()
    }
}
