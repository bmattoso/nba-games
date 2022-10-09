package br.com.nbagames.remote.team.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamListResponse(
    @SerialName("response") val teams: List<TeamResponse>
)