package com.example.rocket.library.rocketlist.domain.model

data class Stage(
    val reusable: Boolean,
    val engines: Int,
    val fuelAmountTons: Double,
    val burnTimeSec: Int?
)
