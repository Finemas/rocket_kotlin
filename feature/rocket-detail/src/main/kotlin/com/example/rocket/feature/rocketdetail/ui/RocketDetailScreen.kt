package com.example.rocket.feature.rocketdetail.ui

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rocket.feature.rocketdetail.presentation.RocketDetailViewModel
import com.example.rocket.feature.rocketdetail.presentation.model.RocketDetailScreenState
import com.example.rocket.library.rocketdetail.domain.model.Length
import com.example.rocket.library.rocketdetail.domain.model.Mass
import com.example.rocket.library.rocketdetail.domain.model.RocketDetail
import com.example.rocket.library.rocketdetail.domain.model.Stage
import com.example.rocket.library.uicore.ui.AppThemePreview
import org.koin.androidx.compose.koinViewModel

fun mockRocketDetail() = RocketDetail(
    description = "Falcon 9 is a reusable, two-stage rocket designed by SpaceX for reliable and safe transport of satellites and the Dragon spacecraft into orbit.",
    height = Length(meters = 70.0, feet = 229.6),
    diameter = Length(meters = 3.7, feet = 12.1),
    mass = Mass(kg = 549054, lb = 1207920),
    firstStage = Stage(
        reusable = true,
        engines = 9,
        fuelAmountTons = 385.0,
        burnTimeSec = 162
    ),
    secondStage = Stage(
        reusable = false,
        engines = 1,
        fuelAmountTons = 90.0,
        burnTimeSec = 397
    ),
    images = listOf(
        "https://example.com/falcon9_image1.jpg",
        "https://example.com/falcon9_image2.jpg"
    )
)

@Composable
fun RocketDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: RocketDetailViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    RocketDetailScreenContent(
        state = state,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDetailScreenContent(
    state: RocketDetailScreenState,
    modifier: Modifier = Modifier,
) {
    val clickAction = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = state.name) },
                navigationIcon =  {
                    IconButton(onClick = { clickAction?.onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RocketDetailStateContent(state = state)
        }
    }
}

@Composable
private fun RocketDetailStateContent(
    state: RocketDetailScreenState,
) {
    // TODO("handle states")
    state.detail?.let { detail ->
        Overview(detail.description)

        Row(
            modifier = Modifier
                .padding(20.dp),
        ) {
            ParameterBox(number = detail.height.meters.toString(), label = "Height")
            ParameterBox(number = detail.diameter.meters.toString(), label = "Diameter")
            ParameterBox(number = detail.mass.kg.toString(), label = "Mass")
        }

        StageView(
            title = "First stage",
            stage = detail.firstStage,
        )

        StageView(
            title = "First stage",
            stage = detail.secondStage,
        )
    } ?: run { 
        Text(text = "No detail")
    }
}

@Preview
@Composable
fun RocketDetailScreenPreview() {
    AppThemePreview {
        val state = RocketDetailScreenState(
            id = "---",
            name = "Falcon 9",
            detail = mockRocketDetail()
        )
        RocketDetailScreenContent(state = state)
    }
}
