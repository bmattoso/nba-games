package br.com.nbagames.remote.game.response

import br.com.nbagames.remote.team.response.TeamResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    @SerialName("gameId") val id: String,
    val arena: String,
    val clock: String,
    val statusGame: String,
    @SerialName("currentPeriod") val currentQuarter: String,
    @SerialName("startTimeUTC") val startTimeUtc: String,
    @SerialName("endTimeUTC") val endTimeUtc: String,
    @SerialName("hTeam") val homeTeam: TeamResponse,
    @SerialName("vTeam") val visitantTeam: TeamResponse
)
