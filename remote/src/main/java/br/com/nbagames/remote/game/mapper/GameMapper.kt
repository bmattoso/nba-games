package br.com.nbagames.remote.game.mapper

import br.com.nbagames.model.Game
import br.com.nbagames.remote.common.extension.toQuarter
import br.com.nbagames.remote.game.response.GameResponse
import br.com.nbagames.remote.team.mapper.TeamMapper

class GameMapper(
    private val teamMapper: TeamMapper
) {
    fun mapLiveGameList(liveGameList: List<GameResponse>): List<Game> {
        return liveGameList.map { gameResponse ->
            Game(
                id = gameResponse.id,
                homeTeam = teamMapper.mapTeamResponseToTeam(gameResponse.teams.home),
                visitorTeam = teamMapper.mapTeamResponseToTeam(gameResponse.teams.visitor),
                homePoints = gameResponse.scores.home.points,
                visitorPoints = gameResponse.scores.visitors.points,
                currentClock = gameResponse.status.clock,
                quarter = gameResponse.periods.current.toQuarter()
            )
        }
    }
}
