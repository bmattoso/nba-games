package br.com.nbagames.repository.game

import br.com.nbagames.model.LiveGame

interface GameRepository {
    suspend fun getLiveGamesFromRemote(): List<LiveGame>
    suspend fun getLiveGamesFromCache(): List<LiveGame>
}
