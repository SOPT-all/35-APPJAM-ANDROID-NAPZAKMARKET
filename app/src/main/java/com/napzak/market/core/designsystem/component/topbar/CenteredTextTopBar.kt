package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 텍스트가 중앙에 위치한 TopBar 컴포넌트
 *
 * @param title TopBar에 표시될 제목
 * @param textStyle 제목 텍스트 스타일
 * @param backgroundColor TopBar의 배경색
 * @param contentColor 제목 텍스트 색상
 * @param modifier TopBar에 적용할 Modifier
 */

@Composable
fun CenteredTextTopBar(
    title: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = NapzakMarketTheme.typography.titleBold18,
    backgroundColor: Color = NapzakMarketTheme.colors.white,
    contentColor: Color = NapzakMarketTheme.colors.black,
    borderColor: Color = NapzakMarketTheme.colors.gray100
)  {
    CommonTopBar(
        title = title,
        navigationIcon = null,
        onNavigationClick = null,
        modifier = modifier,
        textStyle = textStyle,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(borderColor)
    )
}

@Preview(showBackground = true, widthDp = 375, heightDp = 54)
@Composable
fun PreviewMyPageTopBar() {
    NapzakMarketTheme {
        CenteredTextTopBar(title = "마이페이지")
    }
}