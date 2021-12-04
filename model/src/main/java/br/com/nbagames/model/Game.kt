package br.com.nbagames.model

data class Game(
    val id: String,
    val homeTeam: Team,
    val visitantTeam: Team,
    val homePoints: Int,
    val visitantPoints: Int,
    val currentTime: String
)
