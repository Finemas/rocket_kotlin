package com.example.rocket.library.navigation.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Interface for feature navigation.
 *
 * Implement this in every feature that has some navigable destinations
 *
 * @param CrossFeatureNavigationEvent generics that allows specification of cross feature navigation events
 */
interface FeatureNavigation<CrossFeatureNavigationEvent> {

    /**
     * Builds the navigation sub graph for the feature.
     *
     * @param navController the main nav controller
     * @param navigateCrossFeature the cross feature navigation event handling lambda
     */
    fun NavGraphBuilder.buildGraph(
        navController: NavHostController,
        navigateCrossFeature: (navigationEvent: CrossFeatureNavigationEvent) -> Unit,
    )

    /**
     * Builds the deep links for the feature.
     */
    fun DeepLinkNavigationBuilder.buildDeepLinks() {
        // empty deeplinks by default
    }
}
