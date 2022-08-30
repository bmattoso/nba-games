package br.com.nbagames.game.presentation

data class LiveGameListUiState(
    val showLoading: Boolean = true,
    val showEmptyState: Boolean = false,
    val liveGameList: List<GamePresentation> = emptyList(),
    val showError: Boolean = false,
    val liveGameListError: LiveGameListError? = null,
    val isCountdownAvailable: Boolean = true,
    val countdownTimer: String = ""
)
