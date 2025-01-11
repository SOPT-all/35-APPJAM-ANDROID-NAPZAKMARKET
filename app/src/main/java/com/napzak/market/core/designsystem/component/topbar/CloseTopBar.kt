package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R


/**
 * 특정 화면에 사용할 TopBar 변형
 */
@Composable
fun CloseTopBar(
    title: String,
    onCloseClick: () -> Unit
) {
    CommonTopBar(
        title = title,
        navigationIcon = ImageVector.vectorResource(R.drawable.ic_close_24),
        onNavigationClick = onCloseClick,
        textStyle = NapzakMarketTheme.typography.titleSemi18,
        backgroundColor = NapzakMarketTheme.colors.white,
        contentColor = NapzakMarketTheme.colors.black
    )
}


@Preview(showBackground = true, widthDp = 375, heightDp = 54)
@Composable
fun PreviewRegisterSellTopBar() {
    NapzakMarketTheme {
        CloseTopBar(
            title = "팔아요 등록",
            onCloseClick = { /* Preview Action */ }
        )
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 54)
@Composable
fun PreviewRegisterBuyTopBar() {
    NapzakMarketTheme {
        CloseTopBar(
            title = "구해요 등록",
            onCloseClick = { /* Preview Action */ }
        )
    }
}