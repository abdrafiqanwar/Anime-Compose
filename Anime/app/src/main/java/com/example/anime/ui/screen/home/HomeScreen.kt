package com.example.anime.ui.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anime.di.Injection
import com.example.anime.model.FakeAnime
import com.example.anime.ui.ViewModelFactory
import com.example.anime.ui.common.UiState
import com.example.anime.ui.component.AnimeItem
import com.example.anime.ui.theme.AnimeTheme

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllAnime()
            }
            is UiState.Success -> {
                HomeContent(
                    anime = uiState.data,
                    navController = navController,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {
                
            }
        }
    }
}

@Composable
fun HomeContent(
    anime: List<FakeAnime>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Scaffold(
        topBar = { TopBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(anime) { data ->
                AnimeItem(
                    anime = data.anime,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.anime.id)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = "My Anime List")
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("profile") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "profile"
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
fun HomeScreenPreview() {
    AnimeTheme {
        val navController = rememberNavController()
        HomeScreen(
            navController,
            navigateToDetail = {}
        )
    }
}