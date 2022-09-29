package br.com.nbagames.remote.game.service

import br.com.nbagames.remote.game.response.GameListResponse
import br.com.nbagames.remote.statistics.GameStatisticsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET("games/")
    suspend fun getGamesFromDate(@Query("date") date: String): GameListResponse

    @GET("games/")
    suspend fun getGameDetail(@Query("id") gameId: Int): GameListResponse

    @GET("players/statistics")
    suspend fun getGameStatistics(@Query("game") gameId: Int): GameStatisticsResponse
}
