package br.com.nbagames.team.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nbagames.core.error.toCommonError
import br.com.nbagames.usecase.team.LoadFranchiseTeams
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TeamListViewModel(
    private val loadFranchiseTeams: LoadFranchiseTeams
) : ViewModel() {

    private val mutableUiState = MutableStateFlow(TeamListUiState())
    val uiState: StateFlow<TeamListUiState> = mutableUiState

    fun loadFranchiseTeams() {
        viewModelScope.launch(IO) {
            mutableUiState.value = uiState.value.copy(showLoading = true)

            loadFranchiseTeams.invoke()
                .onEach { teams ->
                    updateUiState(uiState.value.copy(showLoading = false, teams = teams, error = null))
                }
                .catch { cause ->
                    updateUiState(uiState.value.copy(showLoading = false, error = cause.toCommonError()))
                }
                .flowOn(IO)
                .collect()
        }
    }

    private fun updateUiState(newState: TeamListUiState) {
        mutableUiState.value = newState
    }
}
