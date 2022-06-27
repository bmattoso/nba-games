package br.com.nbagames.remote.team.mapper

import br.com.nbagames.remote.game.fixture.GameResponseFixture
import br.com.nbagames.remote.team.fixture.TeamResponseFixture
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TeamMapperTest {

    private lateinit var teamMapper: TeamMapper

    @BeforeEach
    fun setup() {
        teamMapper = TeamMapper()
    }

    @Test
    fun `Map team response then return all parsed information`() {
        val parsedGoldenState = teamMapper.mapTeamResponseToTeam(TeamResponseFixture.goldenStateWarriors)

        assertThat(parsedGoldenState.id).isEqualTo(TeamResponseFixture.goldenStateWarriors.id)
        assertThat(parsedGoldenState.name).isEqualTo(TeamResponseFixture.goldenStateWarriors.name)
        assertThat(parsedGoldenState.nickname).isEqualTo(TeamResponseFixture.goldenStateWarriors.nickname)
        assertThat(parsedGoldenState.logo).isEqualTo(TeamResponseFixture.goldenStateWarriors.logo)
    }
}
