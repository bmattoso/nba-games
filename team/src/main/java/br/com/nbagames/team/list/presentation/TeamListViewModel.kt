package br.com.nbagames.team.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamListViewModel : ViewModel() {

    private val mutableUiState = MutableStateFlow(TeamListUiState())
    val uiState: StateFlow<TeamListUiState> = mutableUiState

    fun loadFranchiseTeams() {
        viewModelScope.launch(IO) {
            mutableUiState.value = uiState.value.copy(showLoading = true)
        }
    }
}
