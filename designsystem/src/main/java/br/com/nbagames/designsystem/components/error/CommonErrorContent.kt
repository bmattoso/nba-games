package br.com.nbagames.designsystem.components.error

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.nbagames.core.error.CommonError
import br.com.nbagames.designsystem.components.CommunicationSection

@Composable
fun CommonErrorContent(
    modifier: Modifier = Modifier,
    commonError: CommonError,
    onClickTryAgain: () -> Unit
) {
    CommunicationSection(
        modifier = modifier,
        message = commonError.message,
        animationRes = commonError.animationRes
    ) {
        Button(onClick = onClickTryAgain) {
            Text(text = stringResource(id = commonError.actionMessage))
        }
    }
}