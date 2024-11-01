plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(libs.versions.jvmTarget.get().toInt())
}

gradlePlugin {
    val pluginsPackage = "com.example.rocket.convention.plugin"

    plugins {
        register("localLibrary") {
            id = libs.plugins.local.library.get().pluginId
            implementationClass = "$pluginsPackage.LibraryPlugin"
        }
        register("localCompose") {
            id = libs.plugins.local.compose.get().pluginId
            implementationClass = "$pluginsPackage.ComposePlugin"
        }
    }
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.gradle.android.build)
    implementation(libs.gradle.kotlin.build)
    implementation(libs.detekt.gradle)
    implementation(libs.gradle.compose)
}
