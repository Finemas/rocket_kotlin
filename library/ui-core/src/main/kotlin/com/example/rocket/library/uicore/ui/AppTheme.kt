package com.example.rocket.library.uicore.ui

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppThemePreview(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            content()
        }
    }
}