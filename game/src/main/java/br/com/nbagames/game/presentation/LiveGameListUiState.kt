package br.com.nbagames.game.presentation

import br.com.nbagames.usecase.game.presentation.LiveGamePresentation

data class LiveGameListUiState(
    val showLoading: Boolean = true,
    val showEmptyState: Boolean = false,
    val liveGameList: List<LiveGamePresentation> = emptyList(),
    val showError: Boolean = false,
    val liveGameListError: LiveGameListError? = null
)
