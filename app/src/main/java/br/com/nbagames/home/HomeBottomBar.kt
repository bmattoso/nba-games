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
import br.com.nbagames.core.navigation.HomeRoute
import br.com.nbagames.core.navigation.Route
import br.com.nbagames.designsystem.components.TextField

@Composable
fun HomeBottomBar(
    isContentLoaded: Boolean,
    currentTab: Route,
    onTabSelected: (HomeRoute) -> Unit
) {
    if (isContentLoaded) {
        BottomAppBar(backgroundColor = colorResource(id = R.color.brightGray)) {
            homeBottomBarOptions.forEach { homeTab ->
                val navigationTab = getTabFromRelatedNavigation(currentTab)
                val isSelected = homeTab == navigationTab
                val selectedColor = getColorWhenIsSelected(isSelected)

                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(homeTab.tabIcon),
                            contentDescription = stringResource(id = currentTab.title),
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
}

private fun getColorWhenIsSelected(isSelected: Boolean): Color {
    return if (isSelected) {
        Color.White
    } else {
        Color.Black
    }
}

private fun getTabFromRelatedNavigation(currentTab: Route): HomeRoute {
    if (homeBottomBarOptions.contains(currentTab)) {
        return currentTab as HomeRoute
    }

    return when (currentTab) {
        Route.LiveGameDetail -> HomeRoute.LiveGame
        else -> HomeRoute.LiveGame
    }
}

private val homeBottomBarOptions = listOf(
    HomeRoute.LiveGame,
    HomeRoute.Standings,
    HomeRoute.Teams,
    HomeRoute.Calendar
)
