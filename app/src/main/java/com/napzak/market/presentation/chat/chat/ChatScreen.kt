package com.napzak.market.presentation.chat.chat

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

/**
 * 채팅 화면 하단 바를 표시하는 컴포넌트.
 *
 * 준비 중 상태를 나타내는 아이콘과 텍스트를 포함합니다.
 *
 */

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(NapzakMarketTheme.colors.white)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackTopBar(
            title = "",
            onBackClick = onBackClick,
            modifier = Modifier.align(Alignment.TopCenter),
            showBorder = false,
        )

        EmptyImage(
            modifier = Modifier.padding(top = 278.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatScreenBottomBarPreview() {
    ChatScreen(onBackClick = {})
}
