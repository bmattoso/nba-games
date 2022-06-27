package br.com.nbagames.remote.game.fixture

import br.com.nbagames.remote.game.response.GameTeamsResponse
import br.com.nbagames.remote.team.fixture.TeamResponseFixture

object GameTeamsResponseFixture {

    fun get() = GameTeamsResponse(
        home = TeamResponseFixture.goldenStateWarriors,
        visitor = TeamResponseFixture.bostonCeltics
    )
}
