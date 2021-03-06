package br.com.nbagames.usecase.game.fake

import br.com.nbagames.model.Game
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.Team
import br.com.nbagames.repository.game.GameRepository

class FakeGameRepository : GameRepository {

    var gameList: MutableList<Game> = mutableListOf()
    var throwExceptionRemote: Exception? = null

    override suspend fun getLiveGamesFromRemote(): List<Game> {
        throwExceptionRemote?.let { throw it }

        return gameList
    }

    override suspend fun getLiveGamesFromCache(): List<Game> = gameList

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
                    quarter = Quarter.Fourth
                )
            )
        )

        return gameList
    }
}
