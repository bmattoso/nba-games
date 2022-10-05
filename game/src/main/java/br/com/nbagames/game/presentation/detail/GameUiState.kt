package br.com.nbagames.game.presentation.detail

data class GameUiState(
    val showLoading: Boolean = false,
    val game: GameDetailPresentation? = null
)
