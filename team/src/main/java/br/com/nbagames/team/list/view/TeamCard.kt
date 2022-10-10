package br.com.nbagames.team.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.components.loading.ImageLoader
import br.com.nbagames.designsystem.theme.defaultElevation
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallPadding
import br.com.nbagames.model.Team
import br.com.nbagames.team.R

@Composable
fun TeamCard(
    modifier: Modifier = Modifier,
    team: Team,
    onTeamFavoriteClick: (teamId: Int) -> Unit,
    onTeamClick: (teamId: Int) -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = { onTeamClick(team.id) }),
        elevation = defaultElevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(team.color)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onTeamFavoriteClick(team.id) }
                ) {
                    if (team.isFavorite) {
                        Icon(Icons.Outlined.Star, "Favorite ${team.name}", tint = Color.Yellow)
                    } else {
                        Icon(Icons.Outlined.Star, "Remove ${team.name} from favorites", tint = Color.Gray)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = smallPadding, end = mediumPadding),
                    contentAlignment = Alignment.Center
                ) {
                    ImageLoader(
                        modifier = Modifier.size(80.dp),
                        imageUrl = team.logo,
                        contentDescription = "Team logo",
                        defaultContentResource = R.drawable.default_team_logo
                    )
                }
            }
            Spacer(modifier = Modifier.padding(smallPadding))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(mediumPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = team.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun ColoredTeamCardPreview() {
    val team = Team(
        id = 1,
        name = "Atlanta Hawks",
        nickname = "Hawks",
        logo = "https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png",
        isFavorite = false,
        isFranchise = true,
        color = 556611,
    )

    Surface(
        modifier = Modifier
            .padding(smallPadding)
            .background(Color.LightGray)
    ) {
        TeamCard(team = team, onTeamFavoriteClick = {}, onTeamClick = {})
    }
}
