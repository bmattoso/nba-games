package br.com.nbagames.game.presentation.detail

import br.com.nbagames.game.presentation.GamePresentation

data class GameUiState(
    val showLoadingStatistics: Boolean = true,
    val game: GamePresentation? = null
)
