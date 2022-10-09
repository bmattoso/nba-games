package br.com.nbagames.team.list.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.nbagames.designsystem.theme.NbaGamesTheme

@Composable
fun TeamList(
    modifier: Modifier = Modifier
) {

}

@Preview(showBackground = true)
@Composable
fun TeamListPreview() {
    NbaGamesTheme {
        TeamList()
    }
}
