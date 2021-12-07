package br.com.nbagames.remote.game.mapper

import br.com.nbagames.model.Game
import br.com.nbagames.remote.game.response.GameResponse
import br.com.nbagames.remote.team.mapper.TeamMapper

class GameMapper(
    private val teamMapper: TeamMapper
) {
    fun mapLiveGameList(liveGameList: List<GameResponse>): List<Game> {
        return liveGameList.map { gameResponse ->
            Game(
                id = gameResponse.id,
                homeTeam = teamMapper.mapTeamResponseToTeam(gameResponse.homeTeam),
                visitantTeam = teamMapper.mapTeamResponseToTeam(gameResponse.visitantTeam),
                homePoints = gameResponse.homeTeam.score.points.toInt(),
                visitantPoints = gameResponse.visitantTeam.score.points.toInt(),
                currentClock = gameResponse.clock
            )
        }
    }
}
