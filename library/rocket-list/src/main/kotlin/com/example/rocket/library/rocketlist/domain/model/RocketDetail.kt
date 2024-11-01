package com.example.rocket.library.rocketlist.domain.model

data class RocketDetail(
    val id: String,
    val name: String,
    val description: String,
    val height: Length,
    val diameter: Length,
    val mass: Mass,
    val firstStage: Stage,
    val secondStage: Stage,
    val images: List<String>,
)