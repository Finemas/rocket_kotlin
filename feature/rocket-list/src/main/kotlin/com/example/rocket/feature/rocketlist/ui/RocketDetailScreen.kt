package com.example.rocket.feature.rocketlist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rocket.library.rocketlist.domain.model.Length
import com.example.rocket.library.rocketlist.domain.model.Mass
import com.example.rocket.library.rocketlist.domain.model.RocketDetail
import com.example.rocket.library.rocketlist.domain.model.Stage
import com.example.rocket.library.uicore.ui.AppThemePreview

fun mockRocketDetail() = RocketDetail(
    id = "falcon9",
    name = "Falcon 9",
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
    rocket: RocketDetail = mockRocketDetail(),
) {
    Column(
        modifier = modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Overview(rocket.description)

        Row(
            modifier = Modifier
                .padding(20.dp)
        ) {
            ParameterBox(number = rocket.height.meters.toString(), label = "Height")
            ParameterBox(number = rocket.diameter.meters.toString(), label = "Diameter")
            ParameterBox(number = rocket.mass.kg.toString(), label = "Mass")
        }

        StageView(
            title = "First stage",
            stage = rocket.firstStage
        )

        StageView(
            title = "First stage",
            stage = rocket.secondStage
        )
    }
}

@Preview
@Composable
fun RocketDetailScreenPreview() {
    AppThemePreview {
        RocketDetailScreen()
    }
}