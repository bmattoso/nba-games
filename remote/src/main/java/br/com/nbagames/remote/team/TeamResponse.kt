package br.com.nbagames.remote.team

import br.com.nbagames.remote.game.response.ScoreResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    @SerialName("teamId") val id: String,
    val shortName: String,
    val fullName: String,
    val logo: String,
    val score: ScoreResponse
)
