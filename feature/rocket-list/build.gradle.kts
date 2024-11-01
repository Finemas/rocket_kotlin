plugins {
    alias(libs.plugins.local.library)
    alias(libs.plugins.local.compose)
}

dependencies {
    implementation(projects.library.uiCore)
    implementation(projects.library.rocketList)
    implementation(libs.androidx.navigation)
}