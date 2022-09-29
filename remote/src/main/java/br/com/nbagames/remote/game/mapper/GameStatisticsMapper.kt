package br.com.nbagames.remote.game.mapper

import br.com.nbagames.model.statistics.GameStatistics
import br.com.nbagames.model.statistics.PlayerStatistics
import br.com.nbagames.remote.player.mapper.PlayerMapper
import br.com.nbagames.remote.statistics.GameStatisticsResponse
import br.com.nbagames.remote.statistics.PlayerStatisticsResponse

class GameStatisticsMapper(
    private val playerMapper: PlayerMapper
) {

    fun mapGameStatistics(gameStatisticsResponse: GameStatisticsResponse): GameStatistics {
        val homePlayerStatistics = mutableListOf<PlayerStatistics>()
        val visitorPlayerStatistics = mutableListOf<PlayerStatistics>()

        var firstTeamId = -1
        gameStatisticsResponse.playerStatisticsResponseList.forEach { playerStatisticsResponse ->
            val playerStatistics = mapPlayerStatistics(playerStatisticsResponse)
            if (firstTeamId < 0 || firstTeamId == playerStatisticsResponse.team.id) {
                firstTeamId = playerStatisticsResponse.team.id
                homePlayerStatistics.add(playerStatistics)
            } else {
                visitorPlayerStatistics.add(playerStatistics)
            }
        }

        return GameStatistics(
            homePlayersStatistics = homePlayerStatistics,
            visitorPlayersStatistics = visitorPlayerStatistics
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
            fieldGoalsPercentage = playerStatisticsResponse.fieldGoalsPercentage,
            freeThrowsAttempted = playerStatisticsResponse.freeThrowsAttempted,
            freeThrowsMade = playerStatisticsResponse.freeThrowsMade,
            freeThrowsPercentage = playerStatisticsResponse.freeThrowsPercentage,
            threePointsAttempted = playerStatisticsResponse.threePointsAttempted,
            threePointsMade = playerStatisticsResponse.threePointsMade,
            threePointsPercentage = playerStatisticsResponse.threePointsPercentage,
            offensiveRebound = playerStatisticsResponse.offensiveRebound,
            defensiveRebounds = playerStatisticsResponse.defensiveRebounds,
            totalRebounds = playerStatisticsResponse.totalRebounds
        )
    }
}
