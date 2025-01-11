package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun OnlyTextTopBar() {
    CommonTopBar(
        title = "마이페이지",
        navigationIcon = null,
        onNavigationClick = null,
        textStyle = NapzakMarketTheme.typography.titleBold18,
        backgroundColor = NapzakMarketTheme.colors.white,
        contentColor = NapzakMarketTheme.colors.black
    )
}

@Preview(showBackground = true, widthDp = 375, heightDp = 54)
@Composable
fun PreviewMyPageTopBar() {
    NapzakMarketTheme {
        OnlyTextTopBar()
    }
}