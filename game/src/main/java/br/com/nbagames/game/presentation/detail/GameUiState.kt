package br.com.nbagames.game.presentation.detail

import br.com.nbagames.core.error.CommonError

data class GameUiState(
    val showLoading: Boolean = false,
    val game: GameDetailPresentation? = null,
    val error: CommonError? = null
)
