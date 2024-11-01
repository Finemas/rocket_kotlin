package com.example.rocket.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Wrapper which allows us to write gradle plugins like:
 * ```kotlin
 * class KtPlugin : Plugin<Project> by PluginWrapper factory {
 *     // Implementation
 * }
 * ```
 * instead of
 * ```kotlin
 * class KtPlugin : Plugin<Project> {
 *     override fun apply(target: Project) {
 *         with(target) {
 *             // Implementation
 *         }
 *     }
 * }
 * ```
 * or
 * ```kotlin
 * class KtPlugin : Plugin<Project> by Plugin<Project>(
 *     {
 *         // Implementation
 *     }
 * )
 * ```
 * saving us some indentations and making the plugin more readable.
 */
internal object PluginWrapper {
    infix fun factory(block: Project.() -> Unit) = Plugin<Project> { it.block() }
}