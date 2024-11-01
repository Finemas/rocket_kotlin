package com.example.rocket.feature.rocketlist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocket.library.rocketlist.domain.model.Length
import com.example.rocket.library.rocketlist.domain.model.Mass
import com.example.rocket.library.rocketlist.domain.model.RocketDetail
import com.example.rocket.library.rocketlist.domain.model.Stage

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
    rocket: RocketDetail = mockRocketDetail(),
    modifier: Modifier = Modifier
) {
    Column {
        Overview(rocket.description)


    }
}

@Composable
fun Overview(text: String) {
    Column {
        Text("Overview")
        Text(text)
    }
}

@Composable
fun PurpleBox(number: String, label: String) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(Color(0xFFE57373), shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = number,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}