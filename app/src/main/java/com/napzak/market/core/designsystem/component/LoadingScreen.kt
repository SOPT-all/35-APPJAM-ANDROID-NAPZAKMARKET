package com.napzak.market.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.black70),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = NapzakMarketTheme.colors.purple30
        )
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    NapzakMarketTheme {
        LoadingScreen()
    }
}