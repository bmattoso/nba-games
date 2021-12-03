package br.com.nbagames.repository.game

import br.com.nbagames.model.Game

interface GameRepository {
    suspend fun getLiveGamesFromRemote(): List<Game>
    suspend fun getLiveGamesFromCache(): List<Game>
}
