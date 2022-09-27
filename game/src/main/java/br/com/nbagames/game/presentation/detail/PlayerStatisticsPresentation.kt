package br.com.nbagames.game.presentation.detail

data class PlayerStatisticsPresentation(
    val playerId: Int,
    val playerName: String,
    val points: Int,
    val steals: Int,
    val assists: Int,
    val turnovers: Int,
    val blocks: Int,
    val personalFouls: Int,
    val minutesPlayed: String,
    val playerPosition: String,
    val fieldGoals: String,
    val freeThrows: String,
    val threePoints: String,
    val rebounds: String
)
