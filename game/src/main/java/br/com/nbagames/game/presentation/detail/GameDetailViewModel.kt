package br.com.nbagames.game.presentation.detail

import androidx.lifecycle.ViewModel
import br.com.nbagames.game.R
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
                    homeScore = listOf(20, 12, 30),
                    visitorScore = listOf(10, 20, 27)
                ),
                isGameFinished = false,
                isHalftime = false
            )
        )
    }
}
