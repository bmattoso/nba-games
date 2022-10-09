package br.com.nbagames.remote.team.response

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val id: Int,
    val code: String,
    val name: String,
    val logo: String?
)
