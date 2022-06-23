package br.com.nbagames.remote.game.service

import br.com.nbagames.remote.game.response.GameListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET("games/")
    suspend fun getGamesFromDate(@Query("date") date: String): GameListResponse
}
