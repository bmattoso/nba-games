package br.com.nbagames.game.presentation.detail

import br.com.nbagames.game.presentation.GameDetailPresentation

data class GameUiState(
    val showLoadingStatistics: Boolean = true,
    val game: GameDetailPresentation? = null
)
