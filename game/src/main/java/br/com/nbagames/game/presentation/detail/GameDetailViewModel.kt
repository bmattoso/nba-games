package br.com.nbagames.game.presentation.detail

import androidx.lifecycle.ViewModel
import br.com.nbagames.game.presentation.GameDetailPresentation
import br.com.nbagames.model.Quarter
import br.com.nbagames.model.QuarterScoreHistory
import br.com.nbagames.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameDetailViewModel : ViewModel() {

    private val mutableGameViewState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = mutableGameViewState

    fun loadGameDetails(gameId: Int) {
        mutableGameViewState.value = uiState.value.copy(
            showLoadingStatistics = true,
            game = GameDetailPresentation(
                id = gameId,
                homeTeam = Team(
                    id = 1,
                    name = "Miami Heat",
                    nickname = "MIA",
                    logo = "https://upload.wikimedia.org/wikipedia/fr/thumb/d/de/Houston_Rockets_logo_2003.png/330px" +
                        "-Houston_Rockets_logo_2003.png"
                ),
                visitantTeam = Team(
                    id = 2,
                    name = "Brooklyn Nets",
                    nickname = "BKN",
                    logo = "https://upload.wikimedia.org/wikipedia/fr/8/89/Raptors2015.png"
                ),
                homePoints = 62,
                visitantPoints = 57,
                gameClock = "2:37",
                quarter = Quarter.Third,
                quarterScoreHistory = QuarterScoreHistory(
                    homeScore = listOf(20, 12, 30, 10, 30, 5),
                    visitorScore = listOf(10, 20, 27, 20, 15, 10)
                ),
                isGameFinished = false,
                isHalftime = false
            ),
            gameStatistics = mapGameStatisticsPresentation()
        )
    }

    private fun mapGameStatisticsPresentation(): GameStatisticsPresentation {
        val playersStatistics = listOf(
            PlayerStatisticsPresentation(
                playerId = 1,
                playerName = "D. Booker",
                points = 11,
                steals = 1,
                assists = 2,
                turnovers = 3,
                blocks = 0,
                personalFouls = 1,
                minutesPlayed = "6:33",
                playerPosition = "ST",
                fieldGoals = "6/10 (60%)",
                freeThrows = "2/2 (100%)",
                threePoints = "0/1 (0%)",
                rebounds = "1/0 (1)"
            ),
            PlayerStatisticsPresentation(
                playerId = 2,
                playerName = "J. Tatum",
                points = 11,
                steals = 1,
                assists = 2,
                turnovers = 3,
                blocks = 0,
                personalFouls = 1,
                minutesPlayed = "6:33",
                playerPosition = "ST",
                fieldGoals = "6/10 (60%)",
                freeThrows = "2/2 (100%)",
                threePoints = "0/1 (0%)",
                rebounds = "1/3 (2)"
            )
        )

        return GameStatisticsPresentation(
            homePlayersStatistics = playersStatistics,
            visitorPlayersStatistics = playersStatistics
        )
    }
}
