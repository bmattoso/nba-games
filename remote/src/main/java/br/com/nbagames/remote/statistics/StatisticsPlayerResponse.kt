package br.com.nbagames.remote.statistics

import br.com.nbagames.remote.player.PlayerResponse
import br.com.nbagames.remote.team.response.TeamResponse
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class StatisticsPlayerResponse(
    val team: TeamResponse,
    val player: PlayerResponse,
    val points: Int,
    val steals: Int,
    val assists: Int,
    val turnovers: Int,
    val blocks: Int,
    val comment: String?,
    val plusMinus: String,
    @SerialName("pFouls") val personalFouls: Int,
    @SerialName("min") val minutesPlayed: String,
    @SerialName("pos") val playerPosition: String,
    @SerialName("fga") val fieldGoalsAttempted: Int,
    @SerialName("fgm") val fieldGoalsMade: Int,
    @SerialName("fgp") val fieldGoalsPercentage: String,
    @SerialName("fta") val freeThrowsAttempted: Int,
    @SerialName("ftm") val freeThrowsMade: Int,
    @SerialName("ftp") val freeThrowsPercentage: String,
    @SerialName("tpa") val threePointsAttempted: Int,
    @SerialName("tpm") val threePointsMade: Int,
    @SerialName("tpp") val threePointsPercentage: String,
    @SerialName("offReb") val offensiveRebound: Int,
    @SerialName("defReb") val defensiveRebounds: Int,
    @SerialName("totReb") val totalRebounds: Int
)
