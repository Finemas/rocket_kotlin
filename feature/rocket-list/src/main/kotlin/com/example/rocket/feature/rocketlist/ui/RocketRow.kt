package com.example.rocket.feature.rocketlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rocket.feature.rocketlist.presentation.model.RocketRowState
import com.example.rocket.library.uicore.ui.AppThemePreview

@Composable
fun RocketRow(
    rocket: RocketRowState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(20.dp),
    ) {
        Text(rocket.name)
        Text(rocket.firstFlight)
    }
}

@Preview
@Composable
fun RocketRowPreview() {
    AppThemePreview {
        RocketRow(
            rocket = RocketRowState(
                id = "1",
                name = "Falcon 1",
                firstFlight = "2006-03-24",
            ),
            onClick = {},
        )
    }
}
