package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 뒤로 가기 버튼과 타이틀을 포함한 상단 바 컴포넌트.
 *
 * @param title TopBar에 표시될 제목
 * @param onBackClick 뒤로 가기 버튼 클릭 시 수행할 동작.
 * @param textStyle 제목 텍스트 스타일
 * @param backgroundColor TopBar의 배경색
 * @param contentColor 제목 텍스트 색상
 * @param modifier TopBar에 적용할 Modifier
 */

@Composable
fun BackTopBar(
    title: String,
    onBackClick: () -> Unit,
    textStyle: TextStyle = NapzakMarketTheme.typography.titleSemi18,
    backgroundColor: Color = NapzakMarketTheme.colors.white,
    contentColor: Color = NapzakMarketTheme.colors.black,
    modifier: Modifier = Modifier
) {
    CommonTopBar(
        title = title,
        navigationIcon = ImageVector.vectorResource(R.drawable.ic_back_24),
        onNavigationClick = onBackClick,
        modifier = modifier,
        textStyle = textStyle,
        backgroundColor = backgroundColor,
        contentColor = contentColor
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