package com.example.rocket.feature.rocketlist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rocket.library.rocketlist.domain.model.Stage
import com.example.rocket.library.uicore.ui.AppThemePreview

@Composable
fun StageView(
    title: String,
    stage: Stage,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp)
    ) {
        Column {
            Text(title)

            if (stage.reusable) {
                StageRow(label = "reusable")
            } else {
                StageRow(label = "not reusable")
            }
            StageRow(label = stage.engines.toString() + " engines")
            StageRow(label = stage.fuelAmountTons.toString() + " tons of fuels")
            StageRow(label = stage.burnTimeSec.toString() + " seconds burn time")
        }
    }
}

@Composable
fun StageRow(image: ImageVector = Icons.Default.Home, label: String) {
    Row {
        Icon(
            imageVector = image,
            contentDescription = "Home"
        )
        Text(text = label)
    }
}

@Preview
@Composable
fun StagePreview() {
    AppThemePreview {
        val mockStage = Stage(
            reusable = true,
            engines = 9,
            fuelAmountTons = 385.0,
            burnTimeSec = 162
        )

        StageView(
            title = "First Stage",
            stage = mockStage
        )
    }
}