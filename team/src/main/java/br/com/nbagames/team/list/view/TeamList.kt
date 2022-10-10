package br.com.nbagames.team.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.largePadding
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.model.Team

@Composable
fun TeamList(
    modifier: Modifier = Modifier,
    teams: List<Team>,
    onTeamFavoriteClick: (teamId: Int) -> Unit,
    onTeamClick: (teamId: Int) -> Unit
) {
    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(largePadding),
            verticalArrangement = Arrangement.spacedBy(largePadding),
            contentPadding = PaddingValues(all = mediumPadding)
        ) {
            items(teams.size) { index ->
                TeamCard(
                    modifier = Modifier.defaultMinSize(minWidth = 60.dp),
                    team = teams[index],
                    onTeamFavoriteClick = onTeamFavoriteClick,
                    onTeamClick = onTeamClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamListPreview() {
    NbaGamesTheme {
        TeamList(
            teams = mutableListOf(),
            onTeamFavoriteClick = {},
            onTeamClick = { }
        )
    }
}
