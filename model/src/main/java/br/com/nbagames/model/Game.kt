package br.com.nbagames.model

data class Game(
    val id: Int,
    val homeTeam: Team,
    val visitorTeam: Team,
    val homePoints: Int,
    val visitorPoints: Int,
    val currentClock: String?,
    val quarter: Quarter
)
