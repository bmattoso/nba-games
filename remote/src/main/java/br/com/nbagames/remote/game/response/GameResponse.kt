package br.com.nbagames.remote.game.response

import br.com.nbagames.remote.team.TeamResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    @SerialName("gameId") val id: String,
    val arena: String,
    val gameDuration: String,
    val currentPeriod: String,
    val statusGame: String,
    val startTimeUtc: String,
    val endTimeUtc: String,
    val awayTeam: TeamResponse
)
