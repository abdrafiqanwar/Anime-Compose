package com.example.anime

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.anime.ui.navigation.Screen
import com.example.anime.ui.screen.detail.DetailScreen
import com.example.anime.ui.screen.home.HomeScreen
import com.example.anime.ui.screen.profile.ProfileScreen
import com.example.anime.ui.theme.AnimeTheme

@Composable
fun AnimeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController,
                navigateToDetail = { animeId ->
                    navController.navigate(Screen.Detail.createRoute(animeId))
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("animeId") { type = NavType.LongType}),
        ) {
            val id = it.arguments?.getLong("animeId") ?: -1L
            DetailScreen(
                animeId = id,
                navController = navController,
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
fun AnimeAppPreview() {
    AnimeTheme {
        AnimeApp()
    }
}