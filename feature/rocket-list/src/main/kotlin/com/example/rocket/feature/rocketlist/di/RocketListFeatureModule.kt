package com.example.rocket.feature.rocketlist.di

import com.example.rocket.feature.rocketlist.presentation.RocketListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val rocketListFeatureModule = module {
    viewModelOf(::RocketListViewModel)
}