package com.napzak.market.presentation.prepare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.image.EmptyImage
import com.napzak.market.core.designsystem.component.topbar.BackTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun PrepareScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(NapzakMarketTheme.colors.white)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        BackTopBar(
            title = "",
            onBackClick = onBackClick,
            showBorder = false,
            modifier = Modifier.align(Alignment.TopCenter),
        )

        EmptyImage(
            modifier = Modifier.padding(top = 278.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrepareScreenBottomBarPreview() {
    PrepareScreen(onBackClick = {})
}
