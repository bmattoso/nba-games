package br.com.nbagames.game.presentation.detail

import androidx.lifecycle.ViewModel
import br.com.nbagames.game.R
import br.com.nbagames.game.presentation.GamePresentation
import br.com.nbagames.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameDetailViewModel : ViewModel() {

    private val mutableGameViewState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = mutableGameViewState

    fun loadGameDetails(gameId: Int) {
        mutableGameViewState.value = uiState.value.copy(
            showLoadingStatistics = true,
            game = GamePresentation(
                id = gameId,
                homeTeam = Team(
                    id = 1,
                    name = "Miami Heat",
                    nickname = "MHT",
                    logo = "https://upload.wikimedia.org/wikipedia/fr/thumb/d/de/Houston_Rockets_logo_2003.png/330px" +
                        "-Houston_Rockets_logo_2003.png"
                ),
                visitantTeam = Team(
                    id = 2,
                    name = "Brooklyn Nets",
                    nickname = "BNT",
                    logo = "https://upload.wikimedia.org/wikipedia/fr/8/89/Raptors2015.png"
                ),
                homePoints = 10,
                visitantPoints = 11,
                gameClock = "2:37",
                quarter = R.string.first_quarter
            )
        )
    }
}
