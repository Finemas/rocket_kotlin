package com.example.rocket.library.rocketlist.domain.model

import java.time.LocalDate

data class Rocket(
    val id: String,
    val name: String,
    val firstFlight: LocalDate,
)