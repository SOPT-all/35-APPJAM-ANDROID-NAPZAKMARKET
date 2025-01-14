package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R


/**
 * 특정 화면에서 사용할 수 있는 TopBar 컴포넌트의 변형 버전.
 *
 * @param title TopBar에 표시될 제목 텍스트
 * @param onCloseClick 닫기 아이콘 클릭 시 실행될 동작
 */
@Composable
fun CloseTopBar(
    title: String,
    onCloseClick: () -> Unit,
    textStyle: TextStyle = NapzakMarketTheme.typography.titleSemi18,
    backgroundColor: Color = NapzakMarketTheme.colors.white,
    contentColor: Color = NapzakMarketTheme.colors.black,
    modifier: Modifier = Modifier
) {
    CommonTopBar(
        title = title,
        navigationIcon = ImageVector.vectorResource(R.drawable.ic_close_24),
        onNavigationClick = onCloseClick,
        modifier = modifier,
        textStyle = textStyle,
        backgroundColor = backgroundColor,
        contentColor = contentColor
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