package com.example.rocket.di

import com.example.rocket.feature.rocketdetail.di.rocketDetailFeatureModule
import com.example.rocket.feature.rocketlist.di.rocketListFeatureModule
import com.example.rocket.library.networking.di.networkingModule
import org.koin.dsl.module

val appModule = module {
    includes(
        networkingModule,
        rocketListFeatureModule,
        rocketDetailFeatureModule
    )
}
