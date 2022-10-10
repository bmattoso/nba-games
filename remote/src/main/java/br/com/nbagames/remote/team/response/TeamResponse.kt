package br.com.nbagames.remote.team.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val id: Int,
    val code: String,
    val name: String,
    val logo: String?,
    @SerialName("nbaFranchise") val isFranchise: Boolean? = null,
    @SerialName("allStar") val isAllStar: Boolean? = null
)
