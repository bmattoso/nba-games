package br.com.nbagames.team.list.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.model.Team

@Composable
fun TeamList(
    modifier: Modifier = Modifier,
    teams: List<Team>,
    onTeamClick: (teamId: Int) -> Unit,
) {
    Column(modifier = modifier) {

    }
}

@Preview(showBackground = true)
@Composable
fun TeamListPreview() {
    NbaGamesTheme {
        TeamList(
            teams = mutableListOf(),
            onTeamClick = { }
        )
    }
}
