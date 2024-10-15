package com.example.anime

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anime.ui.navigation.Screen
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
            HomeScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun AnimeAppPreview() {
    AnimeTheme {
        AnimeApp()
    }
}