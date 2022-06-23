package br.com.nbagames.remote.team.response

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val id: Int,
    val nickname: String,
    val name: String,
    val logo: String
)
