package com.example.rocket.feature.rocketdetail.di

import com.example.rocket.feature.rocketdetail.presentation.RocketDetailViewModel
import com.example.rocket.feature.rocketdetail.ui.mockRocketDetail
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val rocketDetailFeatureModule = module {
    viewModelOf(::RocketDetailViewModel)
    viewModel<RocketDetailViewModel> { (id: String, name: String) ->
        // TODO("Pass realdate")
        RocketDetailViewModel(
            id = id,
            name = name,
            rocketDetail = mockRocketDetail()
        )
    }
}
