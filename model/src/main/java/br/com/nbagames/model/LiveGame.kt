package br.com.nbagames.model

data class LiveGame(
    val id: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val homePoints: Int,
    val awayPoints: Int,
    val currentTime: String
)
