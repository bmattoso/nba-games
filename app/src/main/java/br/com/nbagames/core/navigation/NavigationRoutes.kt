package br.com.nbagames.core.navigation

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.nbagames.home.Home
import br.com.nbagames.splash.Splash

@Composable
fun NavigationRoutes(
    startDestination: String = Routes.Splash.name
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val actions = remember(navController) { NavigationDestination(navController, context) }
    val navigationState = rememberSaveable(saver = navStateSaver()) { mutableStateOf(Bundle()) }

    DisposableEffect(Unit) {
        val callback = NavController.OnDestinationChangedListener { navController, _, _ ->
            navigationState.value = navController.saveState() ?: Bundle()
        }
        navController.addOnDestinationChangedListener(callback)
        navController.restoreState(navigationState.value)

        onDispose {
            navController.removeOnDestinationChangedListener(callback)
            navController.enableOnBackPressed(false)
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.Splash.name) {
            Splash(onSetupLoaded = actions::onSetupLoaded)
        }

        composable(Routes.Home.name) {
            Home(onLiveGameClick = actions::onLiveGameClick)
        }

        composable(
            route = Routes.LiveGame.name + "/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.StringType }),
        ) {
        }
    }
}

internal data class NavigationDestination(
    val navController: NavHostController,
    val context: Context
) {
    fun onSetupLoaded() {
        navController.popBackStack()
        navController.navigate(Routes.Home.name)
    }

    fun onLiveGameClick(gameId: String) {
        navController.navigate(Routes.LiveGame.name + "/" + gameId)
    }
}

private fun navStateSaver(): Saver<MutableState<Bundle>, out Any> = Saver(
    save = { it.value },
    restore = { mutableStateOf(it) }
)
