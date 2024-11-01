plugins {
    alias(libs.plugins.local.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
}

dependencies {
    implementation(compose.material3)
    implementation(compose.ui)
    implementation(compose.preview)
    implementation(compose.uiTooling)
    implementation(compose.runtime)
    implementation(compose.foundation)
}

android {
    buildFeatures.compose = true
}