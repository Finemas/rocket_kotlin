plugins {
    alias(libs.plugins.local.library)
    alias(libs.plugins.local.compose)
}

dependencies {
    implementation(projects.feature.rocketDetail)
    implementation(projects.library.uiCore)
    implementation(projects.library.rocketList)
    implementation(projects.core.architecture)
    implementation(libs.androidx.navigation)
}