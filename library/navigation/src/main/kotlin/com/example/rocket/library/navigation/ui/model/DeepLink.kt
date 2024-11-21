package com.example.rocket.library.navigation.ui.model

import androidx.navigation.NavHostController

/**
 * Represents a deep link that can be navigated to.
 *
 * @property basePath The path of the deep link
 * @property parameterKeys The keys of the parameters that the deep link will accept
 * @property navigationBlock The block of code that will be executed when the deep link is navigated to
 */
data class DeepLink(
    val basePath: String,
    val parameterKeys: List<DeepLinkParameterKey> = emptyList(),
    val navigationBlock: NavHostController.(params: Map<DeepLinkParameterKey, DeepLinkParameterValue>) -> Unit,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DeepLink

        if (basePath != other.basePath) return false
        if (parameterKeys != other.parameterKeys) return false

        return true
    }

    override fun hashCode(): Int {
        var result = basePath.hashCode()
        result = 31 * result + parameterKeys.hashCode()
        return result
    }
}
