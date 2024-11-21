package com.example.rocket.feature.rocketdetail.presentation.model

import com.example.rocket.library.rocketdetail.domain.model.RocketDetail

data class RocketDetailScreenState(
    val id: String,
    val name: String,
    val detail: RocketDetail?,
)