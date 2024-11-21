package com.example.rocket.feature.rocketdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.rocket.library.uicore.ui.AppThemePreview

@Composable
fun Overview(
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Overview",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(text)
    }
}

@Preview
@Composable
fun OverviewPreview() {
    AppThemePreview {
        Overview(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vehicula mi eu tellus scelerisque, sit amet volutpat nisi egestas. Sed.",
        )
    }
}
