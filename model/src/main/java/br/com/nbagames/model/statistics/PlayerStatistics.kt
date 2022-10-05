package br.com.nbagames.model.statistics

import br.com.nbagames.model.player.Player

data class PlayerStatistics(
    val player: Player,
    val points: Int,
    val steals: Int,
    val assists: Int,
    val turnovers: Int,
    val blocks: Int,
    val comment: String?,
    val plusMinus: String,
    val personalFouls: Int,
    val minutesPlayed: String,
    val fieldGoalsAttempted: Int,
    val fieldGoalsMade: Int,
    val fieldGoalsPercentage: Double,
    val freeThrowsAttempted: Int,
    val freeThrowsMade: Int,
    val freeThrowsPercentage: Double,
    val threePointsAttempted: Int,
    val threePointsMade: Int,
    val threePointsPercentage: Double,
    val offensiveRebound: Int,
    val defensiveRebounds: Int,
    val totalRebounds: Int
)
