package com.example.rocket.feature.rocketlist.presentation.model

sealed class RocketListNavigationEvent {
    data class ToDetail(val rocketId: String) : RocketListNavigationEvent()
}
