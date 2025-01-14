package com.napzak.market.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientBox(
    modifier: Modifier = Modifier,
    brushColors: List<Color>,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = brushColors,
                )
            ),
    )
}
