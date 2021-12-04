package br.com.nbagames.remote.game.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LiveGameApiResponse(
    @SerialName("games") val liveGameList: List<GameResponse>
)
