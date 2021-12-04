package br.com.nbagames.remote.game.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LiveGameResponse(
    @SerialName("api") val liveGameApiResponse: LiveGameApiResponse
)
