package br.com.nbagames.game.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        Column(modifier = Modifier.padding(smallPadding)) {
            Text(
                text = stringResource(id = R.string.officials),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.padding(smallPadding))
            Row {
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
            imageUrl = official.profilePictureUrl,
            contentDescription = official.id,
            defaultContentResource = R.drawable.ic_user_black,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Text(
            modifier = Modifier.padding(mediumPadding),
            text = official.id,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
@Preview(showBackground = true)
fun GameOfficialsPreview() {
    NbaGamesTheme {
        val official = Official(id = "Scott Foster", profilePictureUrl = "")

        GameOfficials(
            modifier = Modifier.padding(smallPadding),
            officials = listOf(official, official, official)
        )
    }
}
