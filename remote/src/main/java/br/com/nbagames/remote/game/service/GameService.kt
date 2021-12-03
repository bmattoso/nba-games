package br.com.nbagames.remote.game.service

import br.com.nbagames.remote.game.response.LiveGameResponse
import retrofit2.http.GET

interface GameService {

    @GET("games/live")
    fun getLiveGamesNow(): LiveGameResponse
}
