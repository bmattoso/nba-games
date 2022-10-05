package br.com.nbagames.usecase.game.fake

import br.com.nbagames.model.Game
import br.com.nbagames.model.GameStatus
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.QuarterScoreHistory
import br.com.nbagames.model.Team
import br.com.nbagames.model.statistics.GameStatistics
import br.com.nbagames.repository.game.GameRepository

class FakeGameRepository : GameRepository {

    var gameList: MutableList<Game> = mutableListOf()
    var throwExceptionRemote: Exception? = null

    override suspend fun getLiveGamesFromRemote(): List<Game> {
        throwExceptionRemote?.let { throw it }

        return gameList
    }

    override suspend fun getLiveGamesFromCache(): List<Game> = gameList

    override suspend fun getGameDetailById(gameId: Int): Game {
        TODO("Not yet implemented")
    }

    override suspend fun getGameStatistics(gameId: Int): GameStatistics? {
        TODO("Not yet implemented")
    }

    fun populateGameList(): List<Game> {
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

        gameList.addAll(
            listOf(
                Game(
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
                    gameStatistics = null,
                    officials = listOf()
                )
            )
        )

        return gameList
    }
}
