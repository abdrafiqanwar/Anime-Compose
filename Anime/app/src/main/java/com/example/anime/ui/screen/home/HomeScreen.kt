package com.example.anime.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anime.model.dummyAnime
import com.example.anime.ui.component.AnimeItem
import com.example.anime.ui.theme.AnimeTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier
    ) {
        items(dummyAnime, key = { it.title }) { anime ->
            AnimeItem(anime)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    AnimeTheme {
        HomeScreen()
    }
}