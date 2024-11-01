package com.example.rocket.library.rocketlist.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDate {
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(this, dateFormat)
}