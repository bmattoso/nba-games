package br.com.nbagames.remote.game.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScoreResponse(
    val points: Int?,
    @SerialName("linescore") val lineScore: List<String>?
)
