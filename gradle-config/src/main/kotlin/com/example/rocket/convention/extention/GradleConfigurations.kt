package com.example.rocket.convention.extention

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

fun Project.configureDetekt() {
    val detektExtension = extensions.findByType<DetektExtension>()
        ?: error("No detekt extension found, nothing to configure")

    dependencies.apply {
        detektPlugins(versionCatalog.getLibrary("detekt-formatting"))
        detektPlugins(versionCatalog.getLibrary("detekt-compose"))
    }

    val detektDir = rootDir.path
    val projectDir = projectDir.path

    detektExtension.apply {
        buildUponDefaultConfig = true
        autoCorrect = true

        config.setFrom("$detektDir/detekt-config.yml")
        source.setFrom("$projectDir/src")
    }
}