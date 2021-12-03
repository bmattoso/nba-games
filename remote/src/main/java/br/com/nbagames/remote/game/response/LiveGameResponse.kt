package br.com.nbagames.remote.game.response

import kotlinx.serialization.Serializable

@Serializable
data class LiveGameResponse(
    val liveGameList: List<GameResponse>
)
