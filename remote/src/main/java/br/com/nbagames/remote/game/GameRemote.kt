package br.com.nbagames.remote.game

import br.com.nbagames.model.Game
import br.com.nbagames.model.statistics.GameStatistics

interface GameRemote {
    suspend fun getLiveGameList(): List<Game>
    suspend fun getGameDetail(gameId: Int): Game
    suspend fun getGameStatistics(gameId: Int, homeTeamId: Int, visitorTeamId: Int): GameStatistics?
}
