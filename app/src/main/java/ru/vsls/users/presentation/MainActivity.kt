package ru.vsls.users.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.vsls.users.presentation.screens.details.DetailsScreen
import ru.vsls.users.presentation.screens.list.ListScreen
import ru.vsls.users.presentation.navigation.Screen
import ru.vsls.users.ui.theme.UsersTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsersTheme {
                val navController = rememberNavController()
                UserNavHost(
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun UserNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier
    ) {
        composable(Screen.Main.route) {
            ListScreen(
                onNavigateToDetailsScreen = { id ->
                    navController.navigate(Screen.UserDetails.createRoute(id))
                },
            )
        }
        composable(
            route = Screen.UserDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        600, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(600, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        600, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(600, easing = FastOutSlowInEasing),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            DetailsScreen(
                id = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}