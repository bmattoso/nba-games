package br.com.nbagames.repository.game.fake

import br.com.nbagames.model.Game
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.Team
import br.com.nbagames.remote.game.GameRemote

class FakeGameRemote : GameRemote {

    var gameList: MutableList<Game> = mutableListOf()

    override suspend fun getLiveGameList(): List<Game> = gameList

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
