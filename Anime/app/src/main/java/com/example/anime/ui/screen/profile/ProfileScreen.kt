package com.example.anime.ui.screen.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anime.R
import com.example.anime.ui.component.TopBarBack
import com.example.anime.ui.theme.AnimeTheme

@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBarBack(navController, "Profile") },
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "img",
                modifier = modifier
                    .size(200.dp)
            )
            Text(
                text = "Abd. Rafiq Anwar",
                modifier = modifier.padding(vertical = 20.dp),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "abd.rafiqanwar@gmail.com",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
fun ProfileScreenPreview() {
    AnimeTheme {
        val navController = rememberNavController()
        ProfileScreen(navController)
    }
}