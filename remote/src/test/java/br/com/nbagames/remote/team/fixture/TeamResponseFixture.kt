package br.com.nbagames.remote.team.fixture

import br.com.nbagames.remote.team.response.TeamResponse

object TeamResponseFixture {

    val goldenStateWarriors = TeamResponse(
        id = 1,
        name = "Golden State Warriors",
        nickname = "Warriors",
        logo = "https://upload.wikimedia.org/wikipedia/fr/thumb/d/de/Warriors_de_Golden_State_logo.svg/1200px" +
            "-Warriors_de_Golden_State_logo.svg.png"
    )

    val bostonCeltics = TeamResponse(
        id = 2,
        name = "Boston Celtics",
        nickname = "Celtics",
        logo = "https://upload.wikimedia.org/wikipedia/fr/thumb/6/65/Celtics_de_Boston_logo.svg/1024px" +
            "-Celtics_de_Boston_logo.svg.png"
    )
}
