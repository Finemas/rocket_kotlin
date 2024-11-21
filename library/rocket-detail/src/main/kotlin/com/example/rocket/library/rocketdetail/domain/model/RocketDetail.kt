package com.example.rocket.library.rocketdetail.domain.model

data class RocketDetail(
    val description: String,
    val height: Length,
    val diameter: Length,
    val mass: Mass,
    val firstStage: Stage,
    val secondStage: Stage,
    val images: List<String>,
)