package br.com.nbagames.remote.game.mapper

import br.com.nbagames.model.Quarter
import br.com.nbagames.remote.game.fixture.GameResponseFixture
import br.com.nbagames.remote.team.mapper.TeamMapper
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameMapperTest {

    private lateinit var gameMapper: GameMapper

    @BeforeEach
    fun setup() {
        gameMapper = GameMapper(teamMapper = TeamMapper())
    }

    @Test
    fun `Map empty game response list then return empty game list`() {
        assertThat(gameMapper.mapLiveGameList(emptyList())).isEmpty()
    }

    @Test
    fun `Map game response list then return parsed game information`() {
        val liveGameResponseList = GameResponseFixture.getList()

        val liveGameList = gameMapper.mapLiveGameList(liveGameResponseList)

        assertThat(liveGameList).isNotEmpty()
        liveGameList.onEachIndexed { index, game ->
            val expectedGame = liveGameResponseList[index]

            assertThat(game.id).isEqualTo(expectedGame.id)
            assertThat(game.currentClock).isNotNull()
            assertThat(game.currentClock).isEqualTo(expectedGame.status.clock)
            assertThat(game.homePoints).isEqualTo(expectedGame.scores.home.points)
            assertThat(game.visitorPoints).isEqualTo(expectedGame.scores.visitors.points)
            assertThat(game.quarter).isEqualTo(Quarter.Third)
        }
    }

    @Test
    fun `Map game response list then return parsed game home team information`() {
        val liveGameResponseList = GameResponseFixture.getList()

        val liveGameList = gameMapper.mapLiveGameList(liveGameResponseList)

        assertThat(liveGameList).isNotEmpty()
        liveGameList.onEachIndexed { index, game ->
            val expectedHomeTeam = liveGameResponseList[index].teams.home

            assertThat(game.homeTeam.id).isEqualTo(expectedHomeTeam.id)
            assertThat(game.homeTeam.name).isEqualTo(expectedHomeTeam.name)
            assertThat(game.homeTeam.nickname).isEqualTo(expectedHomeTeam.code)
            assertThat(game.homeTeam.logo).isEqualTo(expectedHomeTeam.logo)
        }
    }

    @Test
    fun `Map game response list then return parsed game visitor team information`() {
        val liveGameResponseList = GameResponseFixture.getList()

        val liveGameList = gameMapper.mapLiveGameList(liveGameResponseList)

        assertThat(liveGameList).isNotEmpty()
        liveGameList.onEachIndexed { index, game ->
            val expectedVisitorTeam = liveGameResponseList[index].teams.visitor

            assertThat(game.visitorTeam.id).isEqualTo(expectedVisitorTeam.id)
            assertThat(game.visitorTeam.name).isEqualTo(expectedVisitorTeam.name)
            assertThat(game.visitorTeam.nickname).isEqualTo(expectedVisitorTeam.code)
            assertThat(game.visitorTeam.logo).isEqualTo(expectedVisitorTeam.logo)
        }
    }
}
