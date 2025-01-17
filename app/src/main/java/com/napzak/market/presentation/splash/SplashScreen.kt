package com.napzak.market.presentation.splash

import android.os.Build
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.core.designsystem.component.button.CommonButton
import com.napzak.market.core.designsystem.component.image.SinglePlayLottieAnimation
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.designsystem.theme.Purple30
import com.napzak.market.core.designsystem.theme.White

@Composable
fun SplashRoute(
    onOnboardingNavigate: () -> Unit,
    modifier: Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val view by rememberUpdatedState(LocalView.current)
    val window = remember{ (view.context as? ComponentActivity)?.window }

    LaunchedEffect(key1 = Unit) {
        window?.let {
            setSystemBarStyle(
                window = window,
                view = view,
                isLightTheme = false,
                barColor = Purple30.toArgb()
            )
        }
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            setSystemBarStyle(
                window = window,
                view = view,
                isLightTheme = true,
                barColor = White.toArgb()
            )
        }
    }

    val isStartButtonVisible by viewModel.isStartButtonVisible.collectAsStateWithLifecycle()

    SplashScreen(
        onStartButtonClick = onOnboardingNavigate,
        isStartButtonVisible = isStartButtonVisible,
        onSplashAnimationComplete = {
            viewModel.setIsStartButtonVisible(true)
        },
        modifier = modifier,
    )
}

@Composable
private fun SplashScreen(
    onStartButtonClick: () -> Unit,
    isStartButtonVisible: Boolean = false,
    onSplashAnimationComplete: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.purple30),
    ) {
        SinglePlayLottieAnimation(
            jsonFile = com.napzak.market.R.raw.splash_android,
            modifier = Modifier.fillMaxSize(),
            completionDelay = 1000,
            onLottieComplete = onSplashAnimationComplete,
        )

        AnimatedVisibility(
            visible = isStartButtonVisible,
            enter = fadeIn(
                animationSpec = tween(1000)
            ),
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.BottomCenter),
        ) {
            CommonButton(
                text = "납작마켓 시작하기",
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = NapzakMarketTheme.colors.gray900,
                    contentColor = NapzakMarketTheme.colors.white,
                ),
                contentPadding = PaddingValues(
                    top = 14.dp,
                    bottom = 16.dp,
                    start = 103.dp,
                    end = 105.dp
                ),
                shape = RoundedCornerShape(12.dp),
                textStyle = NapzakMarketTheme.typography.bodyBold16,
                onClick = onStartButtonClick,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 95.dp)
                    .fillMaxWidth()
            )
        }
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

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            it.statusBarColor = barColor
            it.navigationBarColor = barColor
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    NapzakMarketTheme {
        SplashScreen(
            onStartButtonClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
