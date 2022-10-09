package br.com.nbagames.team.list.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.nbagames.designsystem.components.error.CommonErrorContent
import br.com.nbagames.designsystem.components.loading.NbaProgressIndicator
import br.com.nbagames.team.list.presentation.TeamListViewModel

@Composable
fun FranchiseTeamListScreen(
    modifier: Modifier = Modifier,
    onTeamClick: (teamId: Int) -> Unit,
    teamListViewModel: TeamListViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        teamListViewModel.loadFranchiseTeams()
    }

    val uiState = teamListViewModel.uiState.collectAsState().value
    Surface(modifier = modifier) {
        with(uiState) {
            when {
                showLoading -> NbaProgressIndicator(modifier = Modifier.fillMaxSize())
                error != null -> CommonErrorContent(commonError = error) {}
                teams != null -> TeamList(teams = teams, onTeamClick = onTeamClick)
            }
        }
    }
}
