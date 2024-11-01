package com.example.rocket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rocket.feature.rocketlist.ui.RocketDetailScreen
import com.example.rocket.feature.rocketlist.ui.RocketListScreen
import kotlinx.serialization.Serializable

@Serializable
object RocketList
@Serializable
object RocketDetail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    MaterialTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = RocketList,
                enterTransition = { fadeIn(animationSpec = tween(300)) },
                exitTransition = { fadeOut(animationSpec = tween(300)) },
            ) {
                composable<RocketList> {
                    RocketListScreen(
                        onGoToDetail = {
                            navController.navigate(RocketDetail)
                        },
                    )
                }
                composable<RocketDetail> { RocketDetailScreen() }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun GreetingPreview() {
    MaterialTheme {
        MainContent()
    }
}
