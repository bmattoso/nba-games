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
            QuarterScoreHistory(
                homeScore = parseStringListToIntList(scores.home.lineScore),
                visitorScore = parseStringListToIntList(scores.visitors.lineScore)
            )
        }

        return null
    }

    private fun parseStringListToIntList(list: List<String>) = list.map { string -> string.toInt() }
}
