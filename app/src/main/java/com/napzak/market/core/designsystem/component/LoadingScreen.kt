package com.napzak.market.core.designsystem.component

import android.os.Build
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
    modifier: Modifier = Modifier
) {
    val view by rememberUpdatedState(LocalView.current)
    val window = remember { (view.context as? ComponentActivity)?.window }
    val backgroudColor = NapzakMarketTheme.colors.black70

    LaunchedEffect(key1 = Unit) {
        window?.let {
            setSystemBarStyle(
                window = window,
                view = view,
                isLightTheme = false,
                barColor = backgroudColor.toArgb(),
            )
        }
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            setSystemBarStyle(
                window = window,
                view = view,
                isLightTheme = true,
                barColor = backgroudColor.toArgb(),
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroudColor),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = NapzakMarketTheme.colors.purple30
        )
    }
}

private fun setSystemBarStyle(
    window: Window?,
    view: View,
    isLightTheme: Boolean,
    barColor: Int,
) {
    window?.let {
        val controller = WindowCompat.getInsetsController(it, view)
        controller.isAppearanceLightStatusBars = isLightTheme
        controller.isAppearanceLightNavigationBars = isLightTheme

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            it.statusBarColor = barColor
            it.navigationBarColor = barColor
        }
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    NapzakMarketTheme {
        LoadingScreen()
    }
}