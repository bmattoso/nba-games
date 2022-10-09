package br.com.nbagames.team.list.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.nbagames.team.list.presentation.TeamListViewModel

@Composable
fun FranchiseTeamListScreen(
    modifier: Modifier = Modifier,
    onTeamClick: (teamId: Int) -> Unit,
    teamListViewModel: TeamListViewModel = viewModel()
) {

}
