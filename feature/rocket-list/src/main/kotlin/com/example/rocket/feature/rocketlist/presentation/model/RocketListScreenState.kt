package com.example.rocket.feature.rocketlist.presentation.model

data class RocketListScreenState(
    val rockets: List<RocketRowState>,
) {
    companion object {
        fun initState(): RocketListScreenState {
            return RocketListScreenState(
                rockets = listOf(
                    RocketRowState(
                        id = "1",
                        name = "Falcon 1",
                        firstFlight = "2006-03-24",
                    ),
                    RocketRowState(
                        id = "2",
                        name = "Falcon 9",
                        firstFlight = "2010-06-04",
                    ),
                    RocketRowState(
                        id = "3",
                        name = "Falcon Heavy",
                        firstFlight = "2018-02-06",
                    ),
                    RocketRowState(
                        id = "4",
                        name = "Starship",
                        firstFlight = "2021-12-01",
                    ),
                ),
            )
        }
    }
}
