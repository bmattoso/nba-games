package br.com.nbagames.remote.game.mapper

import br.com.nbagames.model.Game
import br.com.nbagames.model.GameStatus
import br.com.nbagames.model.Official
import br.com.nbagames.model.QuarterScoreHistory
import br.com.nbagames.model.toQuarter
import br.com.nbagames.remote.game.response.GameResponse
import br.com.nbagames.remote.game.response.GameScoreResponse
import br.com.nbagames.remote.game.response.GameStatusResponse
import br.com.nbagames.remote.team.mapper.TeamMapper

private const val TOTAL_QUARTERS = 4

class GameMapper(private val teamMapper: TeamMapper) {

    fun mapGame(gameResponse: GameResponse): Game = Game(
        id = gameResponse.id,
        homeTeam = teamMapper.mapTeamResponseToTeam(gameResponse.teams.home),
        visitorTeam = teamMapper.mapTeamResponseToTeam(gameResponse.teams.visitor),
        homePoints = gameResponse.scores.home.points ?: 0,
        visitorPoints = gameResponse.scores.visitors.points ?: 0,
        currentClock = gameResponse.status.clock,
        gameStatus = mapGameStatus(gameResponse.status),
        quarter = gameResponse.periods.current.toQuarter(),
        officials = mapOfficials(gameResponse.officials),
        quarterScoreHistory = mapQuarterScoreHistory(gameResponse.scores),
        gameStatistics = null
    )

    fun mapLiveGameList(liveGameList: List<GameResponse>): List<Game> {
        return liveGameList.map { gameResponse -> mapGame(gameResponse) }
    }

    private fun mapGameStatus(gameStatusResponse: GameStatusResponse): GameStatus {
        if (gameStatusResponse.halftime) return GameStatus.HALF_TIME

        GameStatus.values().forEach { gameStatus ->
            if (gameStatus.code == gameStatusResponse.statusCode) {
                return gameStatus
            }
        }

        return GameStatus.FINISHED
    }

    private fun mapOfficials(officials: List<String>): List<Official> {
        return officials.map { officialName -> Official(id = officialName) }
    }

    private fun mapQuarterScoreHistory(scores: GameScoreResponse): QuarterScoreHistory? {
        if (scores.home.lineScore != null && scores.visitors.lineScore != null) {
            return QuarterScoreHistory(
                homeScore = setupLineScoreByQuarter(scores.home.lineScore.parseToInt()),
                visitorScore = setupLineScoreByQuarter(scores.visitors.lineScore.parseToInt())
            )
        }

        return null
    }

    private fun List<String>.parseToInt() = this.map { string -> string.toInt() }

    private fun setupLineScoreByQuarter(quarterList: List<Int>): List<Int> {
        if (quarterList.size >= TOTAL_QUARTERS) return quarterList

        val scoreByQuarter = mutableListOf<Int>()
        repeat(4) { index ->
            if (index > quarterList.size - 1) {
                scoreByQuarter.add(0)
            } else {
                scoreByQuarter.add(quarterList[index])
            }
        }

        return scoreByQuarter
    }
}
