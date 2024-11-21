package com.example.rocket.feature.rocketlist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rocket.feature.rocketlist.presentation.RocketListViewModel
import com.example.rocket.feature.rocketlist.presentation.model.RocketListAction
import com.example.rocket.feature.rocketlist.presentation.model.RocketListScreenState
import com.example.rocket.feature.rocketlist.presentation.model.RocketRowState
import com.example.rocket.library.uicore.ui.AppThemePreview
import org.koin.androidx.compose.koinViewModel

@Composable
fun RocketListScreen(
    modifier: Modifier = Modifier,
    viewModel: RocketListViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    RocketListScreenContent(
        state = state,
        onGoToDetail = { id, name ->
            viewModel.send(RocketListAction.OpenDetail(id, name))
        },
        modifier = modifier,
    )
}

@Composable
internal fun RocketListScreenContent(
    state: RocketListScreenState,
    onGoToDetail: (rocketId: String, rocketName: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background),
    ) {
        items(state.rockets) { rocket ->
            RocketRow(
                rocket = rocket,
                onClick = {
                    onGoToDetail(rocket.id, rocket.name)
                },
                modifier = Modifier
                    .fillMaxWidth(),
            )

            HorizontalDivider()
        }
    }
}

@Preview
@Composable
fun RocketListPreview() {
    AppThemePreview {
        val state = RocketListScreenState(
            rockets = listOf(
                RocketRowState(
                    id = "1",
                    name = "Falcon 1",
                    firstFlight = "24.03.2006",
                ),
                RocketRowState(
                    id = "2",
                    name = "Falcon 9",
                    firstFlight = "04.06.2010",
                ),
                RocketRowState(
                    id = "3",
                    name = "Falcon Heavy",
                    firstFlight = "06.02.2018",
                ),
                RocketRowState(
                    id = "4",
                    name = "Starship",
                    firstFlight = "01.12.2021",
                ),
            ),
        )
        RocketListScreenContent(
            state = state,
            onGoToDetail = { _, _ -> },
            modifier = Modifier
                .background(Color.Red),
        )
    }
}
