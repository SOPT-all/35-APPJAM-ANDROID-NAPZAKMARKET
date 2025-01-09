package com.napzak.market.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object NapzakMarketTheme {
    val colors: NapzakMarketColors
        @Composable
        @ReadOnlyComposable
        get() = localNapzakMarketColors.current
    val typography: NapzakMarketTypography
        @Composable
        @ReadOnlyComposable
        get() = localNapzakMarketTypography.current
}

@Composable
fun ProvideNapzakMarketColorsAndTypography(
    colors: NapzakMarketColors,
    typography: NapzakMarketTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localNapzakMarketColors provides colors,
        localNapzakMarketTypography provides typography,
        content = content
    )
}

@Composable
fun NapzakMarketTheme(
    content: @Composable () -> Unit
) {
    ProvideNapzakMarketColorsAndTypography(
        colors = defaultNapzakMarketColors,
        typography = defaultNapzakMarketTypography
    ) {
        MaterialTheme(
            content = content
        )
    }
}