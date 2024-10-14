package com.example.anime.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anime.R
import com.example.anime.model.Anime

@Composable
fun AnimeItem(
    anime: Anime,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row {
            Column(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = painterResource(anime.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(120.dp)
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(
                        text = anime.title,
                        maxLines = 2,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row {
                    Text(
                        text = anime.type
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeItemPreview() {
    MaterialTheme {
        AnimeItem(
            anime = Anime("Boruto: Naruto Next Generations",
                "TV, Spring 2017", R.drawable.boruto, "6.03", "Following the successful end of the Fourth Shinobi World War, Konohagakure has been enjoying a period of peace, prosperity, and extraordinary technological advancement. This is all due to the efforts of the Allied Shinobi Forces and the village\\'s Seventh Hokage, Naruto Uzumaki. Now resembling a modern metropolis, Konohagakure has changed, particularly the life of a shinobi. Under the watchful eye of Naruto and his old comrades, a new generation of shinobi has stepped up to learn the ways of the ninja.\\n Boruto Uzumaki is often the center of attention as the son of the Seventh Hokage. Despite having inherited Naruto\\'s boisterous and stubborn demeanor, Boruto is considered a prodigy and is able to unleash his potential with the help of supportive friends and family. Unfortunately, this has only worsened his arrogance and his desire to surpass Naruto which, along with his father\\'s busy lifestyle, has strained their relationship. However, a sinister force brewing within the village may threaten Boruto\\'s carefree life."),
            modifier = Modifier.padding(8.dp)
        )
    }
}