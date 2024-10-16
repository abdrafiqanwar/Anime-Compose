package com.example.anime.ui.screen.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anime.R
import com.example.anime.di.Injection
import com.example.anime.ui.ViewModelFactory
import com.example.anime.ui.common.UiState
import com.example.anime.ui.component.TopBarBack
import com.example.anime.ui.theme.AnimeTheme

@Composable
fun DetailScreen(
    animeId: Long,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAnimeById(animeId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.anime.title,
                    data.anime.type,
                    data.anime.image,
                    data.anime.rating,
                    data.anime.synopsis,
                    navController = navController
                )

            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun DetailContent(
    title: String,
    type: String,
    @DrawableRes image: Int,
    rating: String,
    synopsis: String,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { TopBarBack(navController, "Detail") }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(240.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Score",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = modifier.size(28.dp)
                        )
                        Text(
                            text = rating,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(
                        modifier = modifier.fillMaxWidth().height(20.dp)
                    )
                    Text(
                        text = "Type",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = type,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(vertical = 20.dp)
            )
            Text(
                text = synopsis,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
fun DetailScreenPreview() {
    AnimeTheme {
        val navController = rememberNavController()
        DetailContent(
        "Boruto: Naruto Next Generations",
            "TV, Spring 2017",
            R.drawable.boruto,
            "6.03",
            "Following the successful end of the Fourth Shinobi World War, Konohagakure has been enjoying a period of peace, prosperity, and extraordinary technological advancement. This is all due to the efforts of the Allied Shinobi Forces and the village\\'s Seventh Hokage, Naruto Uzumaki. Now resembling a modern metropolis, Konohagakure has changed, particularly the life of a shinobi. Under the watchful eye of Naruto and his old comrades, a new generation of shinobi has stepped up to learn the ways of the ninja.\\n Boruto Uzumaki is often the center of attention as the son of the Seventh Hokage. Despite having inherited Naruto\\'s boisterous and stubborn demeanor, Boruto is considered a prodigy and is able to unleash his potential with the help of supportive friends and family. Unfortunately, this has only worsened his arrogance and his desire to surpass Naruto which, along with his father\\'s busy lifestyle, has strained their relationship. However, a sinister force brewing within the village may threaten Boruto\\'s carefree life.",
            navController = navController
        )
    }
}