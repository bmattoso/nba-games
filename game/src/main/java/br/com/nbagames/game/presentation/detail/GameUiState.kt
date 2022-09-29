package br.com.nbagames.game.presentation.detail

import br.com.nbagames.game.presentation.GameDetailPresentation

data class GameUiState(
    val showLoading: Boolean = false,
    val game: GameDetailPresentation? = null
)
