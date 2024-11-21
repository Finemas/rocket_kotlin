package com.example.rocket.feature.rocketlist.presentation.model

sealed class RocketListAction {
    data class OpenDetail(val id: String) : RocketListAction()
}
