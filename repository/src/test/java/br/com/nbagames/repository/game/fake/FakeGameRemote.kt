package br.com.nbagames.repository.game.fake

import br.com.nbagames.model.Game
import br.com.nbagames.model.GameStatus
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.QuarterScoreHistory
import br.com.nbagames.model.Team
import br.com.nbagames.model.statistics.GameStatistics
import br.com.nbagames.remote.game.GameRemote
import java.util.Date

class FakeGameRemote : GameRemote {

    var gameList: MutableList<Game> = mutableListOf()

    override suspend fun getLiveGameList(): List<Game> = gameList

    override suspend fun getGamesFromDate(date: Date): List<Game> = gameList

    override suspend fun getGameDetail(gameId: Int): Game {
        val homeTeam = Team(
            id = 100,
            name = "Brooklyn Nets",
            nickname = "BKN",
            logo = ""
        )
        val visitorTeam = Team(
            id = 200,
            name = "Miami Heat",
            nickname = "MIA",
            logo = ""
        )

        return createGame(homeTeam, visitorTeam)
    }

    override suspend fun getGameStatistics(gameId: Int, homeTeamId: Int, visitorTeamId: Int): GameStatistics? {
        TODO("Not yet implemented")
    }

    fun populateGameList(): MutableList<Game> {
        val homeTeam = Team(
            id = 100,
            name = "Brooklyn Nets",
            nickname = "BKN",
            logo = ""
        )
        val visitorTeam = Team(
            id = 200,
            name = "Miami Heat",
            nickname = "MIA",
            logo = ""
        )

        gameList.addAll(listOf(createGame(homeTeam, visitorTeam)))

        return gameList
    }

    fun createGame(
        homeTeam: Team,
        visitorTeam: Team
    ): Game = Game(
        id = 123,
        homeTeam = homeTeam,
        visitorTeam = visitorTeam,
        homePoints = 130,
        visitorPoints = 127,
        currentClock = "00:23",
        quarter = Quarter.Fourth,
        gameStatus = GameStatus.RUNNING,
        quarterScoreHistory = QuarterScoreHistory(
            homeScore = listOf(10, 20, 30, 40),
            visitorScore = listOf(11, 17, 23, 37)
        ),
        officials = emptyList(),
        gameStatistics = null
    )
}
