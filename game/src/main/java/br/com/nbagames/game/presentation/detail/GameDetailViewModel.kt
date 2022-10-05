package br.com.nbagames.game.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nbagames.model.Game
import br.com.nbagames.usecase.game.LoadGameDetail
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GameDetailViewModel(
    private val loadGameDetail: LoadGameDetail
) : ViewModel() {

    private val mutableGameViewState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = mutableGameViewState

    fun loadGameDetails(gameId: Int) {
        mutableGameViewState.value = uiState.value.copy(showLoading = true)

        viewModelScope.launch {
            loadGameDetail(gameId)
                .map { game -> mapGameToUiState(game) }
                .onEach { gameDetailPresentation ->
                    mutableGameViewState.value = uiState.value.copy(
                        showLoading = false,
                        game = gameDetailPresentation
                    )
                }
                .catch { throwable ->
                    Log.e("BLA", throwable.toString(), throwable)
                }
                .flowOn(IO)
                .collect()
        }
    }

    private fun mapGameToUiState(game: Game): GameDetailPresentation {
        return GameDetailPresentation(
            id = game.id,
            homeTeam = game.homeTeam,
            visitantTeam = game.visitorTeam,
            homePoints = game.homePoints,
            visitantPoints = game.visitorPoints,
            gameClock = game.currentClock,
            quarter = game.quarter,
            isGameFinished = false,
            isHalftime = false,
            quarterScoreHistory = game.quarterScoreHistory,
            gameStatistics = game.gameStatistics,
            officials = game.officials
        )
    }
}
