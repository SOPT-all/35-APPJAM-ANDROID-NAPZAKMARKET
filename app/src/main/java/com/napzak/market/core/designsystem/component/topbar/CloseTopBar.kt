package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    modifier: Modifier = Modifier,
    textStyle: TextStyle = NapzakMarketTheme.typography.titleSemi18,
    backgroundColor: Color = NapzakMarketTheme.colors.white,
    contentColor: Color = NapzakMarketTheme.colors.gray900,
) {
    CommonTopBar(
        modifier = modifier
            .padding(vertical = 13.dp, horizontal = 20.dp),
        title = title,
        navigationIcon = ImageVector.vectorResource(R.drawable.ic_close_24),
        onNavigationClick = onCloseClick,
        textStyle = textStyle,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterSellTopBar() {
    NapzakMarketTheme {
        CloseTopBar(
            title = "팔아요 등록",
            onCloseClick = { /* Preview Action */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterBuyTopBar() {
    NapzakMarketTheme {
        CloseTopBar(
            title = "구해요 등록",
            onCloseClick = { /* Preview Action */ }
        )
    }
}
