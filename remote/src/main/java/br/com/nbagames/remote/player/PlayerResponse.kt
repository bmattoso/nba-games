package br.com.nbagames.remote.player

@kotlinx.serialization.Serializable
data class PlayerResponse(
    val id: Int,
    val firstName: String,
    val lastName: String
)
