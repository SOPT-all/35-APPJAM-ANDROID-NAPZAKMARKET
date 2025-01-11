package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun BackTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    CommonTopBar(
        title = title,
        navigationIcon = ImageVector.vectorResource(R.drawable.ic_back_24),
        onNavigationClick = onBackClick,
        textStyle = NapzakMarketTheme.typography.titleSemi18,
        backgroundColor = NapzakMarketTheme.colors.white,
        contentColor = NapzakMarketTheme.colors.black
    )
}

@Preview(showBackground = true, widthDp = 375, heightDp = 54)
@Composable
fun PreviewGenreTopBar() {
    NapzakMarketTheme {
        BackTopBar(
            title = "장르",
            onBackClick = { /* Preview Action */ }
        )
    }
}