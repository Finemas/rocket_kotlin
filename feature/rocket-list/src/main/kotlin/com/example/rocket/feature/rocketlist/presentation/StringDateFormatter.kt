package com.example.rocket.feature.rocketlist.presentation

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formatToStringDate(): String {
    val formater = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return formater.format(this)
}