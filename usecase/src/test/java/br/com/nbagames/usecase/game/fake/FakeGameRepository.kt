package br.com.nbagames.usecase.game.fake

import br.com.nbagames.model.Game
import br.com.nbagames.repository.game.GameRepository

class FakeGameRepository : GameRepository {

    override suspend fun getLiveGamesFromRemote(): List<Game> {
        return emptyList()
    }

    override suspend fun getLiveGamesFromCache(): List<Game> {
        return emptyList()
    }
}
