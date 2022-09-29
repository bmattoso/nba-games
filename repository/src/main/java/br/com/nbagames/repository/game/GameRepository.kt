package br.com.nbagames.repository.game

import br.com.nbagames.model.Game
import br.com.nbagames.model.statistics.GameStatistics

interface GameRepository {
    suspend fun getLiveGamesFromRemote(): List<Game>
    suspend fun getLiveGamesFromCache(): List<Game>
    suspend fun getGameDetailById(gameId: Int): Game
    suspend fun getGameStatistics(gameId: Int): GameStatistics?
}
