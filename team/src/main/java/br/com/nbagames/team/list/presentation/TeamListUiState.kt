package br.com.nbagames.team.list.presentation

import br.com.nbagames.core.error.CommonError
import br.com.nbagames.model.Team

data class TeamListUiState(
    val showLoading: Boolean = true,
    val error: CommonError? = null,
    val teams: List<Team>? = null
)
