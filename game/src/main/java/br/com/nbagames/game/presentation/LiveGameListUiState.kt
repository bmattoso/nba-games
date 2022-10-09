package br.com.nbagames.game.presentation

import br.com.nbagames.core.error.CommonError

data class LiveGameListUiState(
    val showLoading: Boolean = true,
    val showEmptyState: Boolean = false,
    val liveGameList: List<GamePresentation> = emptyList(),
    val showError: Boolean = false,
    val liveGameListError: CommonError? = null,
    val isCountdownAvailable: Boolean = true,
    val countdownTimer: String = ""
)
