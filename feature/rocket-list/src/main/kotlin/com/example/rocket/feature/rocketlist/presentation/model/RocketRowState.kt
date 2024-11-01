package com.example.rocket.feature.rocketlist.presentation.model

import com.example.rocket.feature.rocketlist.presentation.formatToStringDate
import com.example.rocket.library.rocketlist.domain.model.Rocket

data class RocketRowState(
    val id: String,
    val name: String,
    val firstFlight: String,
)

fun Rocket.toUiState(): RocketRowState {
    return RocketRowState(
        id = id,
        name = name,
        firstFlight = firstFlight.formatToStringDate()
    )
}