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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rocket.feature.rocketlist.presentation.RocketListViewModel
import com.example.rocket.feature.rocketlist.presentation.model.RocketListNavigationEvent
import com.example.rocket.feature.rocketdetail.ui.RocketDetailScreen
import com.example.rocket.feature.rocketlist.ui.RocketListScreen
import com.example.rocket.library.navigation.ui.Destination
import com.example.rocket.library.navigation.ui.createDestinationPathWithParams
import com.example.rocket.library.navigation.ui.destination
import com.example.rocket.library.navigation.ui.navigateToDestination
import com.example.rocket.library.navigation.ui.navigatingViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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
                .background(Color.LightGray),
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = RocketList.destinationName,
                enterTransition = { fadeIn(animationSpec = tween(300)) },
                exitTransition = { fadeOut(animationSpec = tween(300)) },
            ) {
                destination(route = RocketList) {
                    val viewModel = navigatingViewModel<RocketListNavigationEvent, RocketListViewModel> { event ->
                        when (event) {
                            is RocketListNavigationEvent.ToDetail -> {
                                navController.navigateToDestination(
                                    RocketDetail,
                                    mapOf(RocketDetail.ROCKET_ID to event.rocketId),
                                )
                            }
                        }
                    }
                    RocketListScreen(viewModel = viewModel)
                }

                destination(
                    route = RocketDetail,
                    arguments = listOf(
                        navArgument(name = RocketDetail.ROCKET_ID) {
                            this.type = NavType.StringType
                        },
                    ),
                ) { entry ->
                    val id = entry.arguments!!.getString(RocketDetail.ROCKET_ID)
                    val viewModel = koinViewModel<com.example.rocket.feature.rocketdetail.presentation.RocketDetailViewModel>(parameters = { parametersOf(id) })
                    RocketDetailScreen(viewModel = viewModel)
                }
            }
        }
    }
}

internal data object RocketList : Destination {
    override val destinationName: String = "rocket_list"
}

internal data object RocketDetail : Destination {
    override val destinationName: String = "rocket_detail"

    override fun createDestinationPath(): String = createDestinationPathWithParams(listOf(ROCKET_ID))

    const val ROCKET_ID = "rocket_id"
}

@Preview(showBackground = false)
@Composable
private fun GreetingPreview() {
    MaterialTheme {
        MainContent()
    }
}
