package br.com.nbagames.core.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.nbagames.home.Home
import br.com.nbagames.splash.Splash

@Composable
fun NavigationRoutes(startDestination: String = Routes.Splash.name) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val actions = remember(navController) { NavigationDestination(navController, context) }
    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.Splash.name) {
            Splash(
                onSetupLoaded = actions::onSetupLoaded
            )
        }

        composable(Routes.Home.name) {
            Home()
        }
    }
}

internal data class NavigationDestination(
    val navController: NavHostController,
    val context: Context
) {
    fun onSetupLoaded() {
    }
}