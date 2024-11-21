package com.example.rocket.library.navigation.ui

import androidx.navigation.NamedNavArgument

/**
 * Interface for defining a destination in the feature navigation object.
 */
interface Destination {

    /**
     * The name of the destination. It is used to create the path to this destination.
     */
    val destinationName: String

    /**
     * Create a path to this destination.
     * By default, the path is the destination name.
     * Override this method if you need to pass parameters to the destination.
     *
     * Note: This is implemented as a method instead of a property for purpose. If it is implemented as property it would be dependent on
     * the order of the properties in the class which can be confusing and can lead to bugs.
     *
     * @return the path to this destination
     */
    fun createDestinationPath(): String = destinationName
}
