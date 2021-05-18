package br.com.nbagames.model

data class LiveGame(
    val id: String,
    val homeTeam: String,
    val awayTeam: String,
    val homePoints: Int,
    val awayPoints: Int
)