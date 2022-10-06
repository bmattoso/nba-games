package br.com.nbagames.remote.game.mapper

import br.com.nbagames.model.statistics.GameStatistics
import br.com.nbagames.model.statistics.PlayerStatistics
import br.com.nbagames.remote.player.mapper.PlayerMapper
import br.com.nbagames.remote.statistics.GameStatisticsResponse
import br.com.nbagames.remote.statistics.PlayerStatisticsResponse

class GameStatisticsMapper(private val playerMapper: PlayerMapper) {

    fun mapGameStatistics(
        gameStatisticsResponse: GameStatisticsResponse,
        homeTeamId: Int,
        visitorTeamId: Int
    ): GameStatistics {
        val homePlayingPlayerStatistics = mutableListOf<PlayerStatistics>()
        val homeBenchPlayerStatistics = mutableListOf<PlayerStatistics>()
        val visitorPlayingPlayerStatistics = mutableListOf<PlayerStatistics>()
        val visitorBenchPlayerStatistics = mutableListOf<PlayerStatistics>()

        gameStatisticsResponse.playerStatisticsResponseList.forEach { playerStatisticsResponse ->
            val playerStatistics = mapPlayerStatistics(playerStatisticsResponse)
            if (homeTeamId == playerStatisticsResponse.team.id) {
                if (playerStatisticsResponse.playerPosition.isNullOrBlank()) {
                    homeBenchPlayerStatistics.add(playerStatistics)
                } else {
                    homePlayingPlayerStatistics.add(playerStatistics)
                }
            } else if (visitorTeamId == playerStatisticsResponse.team.id) {
                if (playerStatisticsResponse.playerPosition.isNullOrBlank()) {
                    visitorBenchPlayerStatistics.add(playerStatistics)
                } else {
                    visitorPlayingPlayerStatistics.add(playerStatistics)
                }
            }
        }

        return GameStatistics(
            homePlayingPlayers = homePlayingPlayerStatistics,
            homeBenchPlayers = homeBenchPlayerStatistics,
            visitorPlayingPlayers = visitorPlayingPlayerStatistics,
            visitorBenchPlayers = visitorBenchPlayerStatistics
        )
    }

    private fun mapPlayerStatistics(playerStatisticsResponse: PlayerStatisticsResponse): PlayerStatistics {
        val player = playerMapper
            .mapPlayer(playerResponse = playerStatisticsResponse.player)
            .copy(position = playerStatisticsResponse.playerPosition)

        return PlayerStatistics(
            player = player,
            points = playerStatisticsResponse.points,
            steals = playerStatisticsResponse.steals,
            assists = playerStatisticsResponse.assists,
            turnovers = playerStatisticsResponse.turnovers,
            blocks = playerStatisticsResponse.blocks,
            comment = playerStatisticsResponse.comment,
            plusMinus = playerStatisticsResponse.plusMinus,
            personalFouls = playerStatisticsResponse.personalFouls,
            minutesPlayed = playerStatisticsResponse.minutesPlayed,
            fieldGoalsAttempted = playerStatisticsResponse.fieldGoalsAttempted,
            fieldGoalsMade = playerStatisticsResponse.fieldGoalsMade,
            fieldGoalsPercentage = playerStatisticsResponse.fieldGoalsPercentage.toDoubleOrZero(),
            freeThrowsAttempted = playerStatisticsResponse.freeThrowsAttempted,
            freeThrowsMade = playerStatisticsResponse.freeThrowsMade,
            freeThrowsPercentage = playerStatisticsResponse.freeThrowsPercentage.toDoubleOrZero(),
            threePointsAttempted = playerStatisticsResponse.threePointsAttempted,
            threePointsMade = playerStatisticsResponse.threePointsMade,
            threePointsPercentage = playerStatisticsResponse.threePointsPercentage.toDoubleOrZero(),
            offensiveRebound = playerStatisticsResponse.offensiveRebound,
            defensiveRebounds = playerStatisticsResponse.defensiveRebounds,
            totalRebounds = playerStatisticsResponse.totalRebounds
        )
    }

    private fun String.toDoubleOrZero(): Double = try {
        this.toDouble()
    } catch (ex: NumberFormatException) {
        // TODO Log error
        0.0
    }
}
