package br.com.nbagames.usecase.game.fake

import br.com.nbagames.model.Game
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.Team
import br.com.nbagames.repository.game.GameRepository

class FakeGameRepository : GameRepository {

    private val gameList: MutableList<Game> = mutableListOf()

    override suspend fun getLiveGamesFromRemote(): List<Game> = gameList

    override suspend fun getLiveGamesFromCache(): List<Game> = gameList

    fun clearGameList() {
        gameList.clear()
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
                    quarter = Quarter.Fourth
                )
            )
        )

        return gameList
    }
}
