package com.napzak.market.core.designsystem.component.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import kotlinx.coroutines.delay

@Composable
fun SinglePlayLottieAnimation(
    jsonFile: Int,
    modifier: Modifier = Modifier,
    completionDelay: Long = 0,
    onLottieComplete: () -> Unit = {},
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(jsonFile))
    val animationState = rememberLottieAnimatable()

    LaunchedEffect(lottieComposition) {
        lottieComposition?.let {
            animationState.animate(
                composition = it,
            )
            delay(completionDelay)
            onLottieComplete()
        }
    }

    LottieAnimation(
        modifier = modifier,
        composition = lottieComposition,
        progress = { animationState.progress },
        clipToCompositionBounds = false,

    )
}

@Preview
@Composable
private fun NapzakLottieAnimationPreview() {
    NapzakMarketTheme {
        SinglePlayLottieAnimation(
            jsonFile = com.napzak.market.R.raw.splash_android,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}