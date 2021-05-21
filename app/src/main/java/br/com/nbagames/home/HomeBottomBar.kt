package br.com.nbagames.home

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.nbagames.R
import br.com.nbagames.designsystem.components.TextField

@Composable
fun HomeBottomBar(
    currentTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    BottomAppBar(backgroundColor = colorResource(id = R.color.brightGray)) {
        HomeTab.values().forEach { homeTab ->
            val isSelected = homeTab == currentTab
            val selectedColor = getColorWhenIsSelected(isSelected)

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(homeTab.tabIcon),
                        contentDescription = stringResource(id = currentTab.tabName),
                        tint = selectedColor,
                        modifier = Modifier.size(25.dp)
                    )
                },
                label = {
                    TextField(
                        text = stringResource(homeTab.tabName),
                        fontSize = 12.sp,
                        color = selectedColor
                    )
                },
                selected = isSelected,
                onClick = { onTabSelected(homeTab) },
            )
        }
    }
}

private fun getColorWhenIsSelected(isSelected: Boolean): Color {
    return if (isSelected) {
        Color.White
    } else {
        Color.Black
    }
}
