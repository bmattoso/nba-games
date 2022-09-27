package br.com.nbagames.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.nbagames.designsystem.components.loading.ImageLoader
import br.com.nbagames.designsystem.theme.NbaGamesTheme
import br.com.nbagames.designsystem.theme.defaultElevation
import br.com.nbagames.designsystem.theme.mediumPadding
import br.com.nbagames.designsystem.theme.smallPadding
import br.com.nbagames.game.R
import br.com.nbagames.model.Official

@Composable
fun GameOfficials(
    modifier: Modifier = Modifier,
    officials: List<Official>
) {
    Card(
        modifier = modifier,
        elevation = defaultElevation
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            officials.forEach { official ->
                OfficialCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(smallPadding),
                    official = official
                )
            }
        }
    }
}

@Composable
fun OfficialCard(
    modifier: Modifier = Modifier,
    official: Official
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLoader(
            imageUrl = official.getStoragePath(),
            contentDescription = official.id,
            defaultContentResource = R.drawable.default_team_logo,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Text(
            modifier = Modifier.padding(mediumPadding),
            text = official.id,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun GameOfficialsPreview() {
    NbaGamesTheme {
        GameOfficials(
            modifier = Modifier.padding(smallPadding),
            officials = listOf(Official.ScottFoster, Official.ScottFoster, Official.ScottFoster)
        )
    }
}
