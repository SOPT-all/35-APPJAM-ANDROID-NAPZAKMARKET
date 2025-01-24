package com.napzak.market.core.designsystem.component

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun LoadingScreen(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    isLightTheme: Boolean = false,
) {
    val view by rememberUpdatedState(LocalView.current)
    val window = remember { (view.context as? ComponentActivity)?.window }
    val originalColor = NapzakMarketTheme.colors.white
    val loadingColor = NapzakMarketTheme.colors.black70

    LaunchedEffect(isLoading) {
        window?.let {
            val statusBarColor = if (isLoading) loadingColor else originalColor
            val navigationBarColor = if (isLoading) loadingColor else originalColor

            it.statusBarColor = statusBarColor.toArgb()
            it.navigationBarColor = navigationBarColor.toArgb()

            val controller = WindowCompat.getInsetsController(it, view)
            controller.isAppearanceLightStatusBars = !isLoading
            controller.isAppearanceLightNavigationBars = !isLoading
        }
    }

    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(loadingColor),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = NapzakMarketTheme.colors.purple30
            )
        }
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    NapzakMarketTheme {
        LoadingScreen(
            isLoading = true,
            isLightTheme = true
        )
    }
}