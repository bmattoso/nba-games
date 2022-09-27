package br.com.nbagames.remote.player

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PlayerResponse(
    val id: Int,
    @SerialName("firstname") val firstName: String,
    @SerialName("lastname") val lastName: String
)
