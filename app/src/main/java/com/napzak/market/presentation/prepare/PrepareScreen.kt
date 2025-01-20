package com.napzak.market.presentation.prepare

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.image.EmptyImage

/**
 * 채팅 화면 하단 바를 표시하는 컴포넌트.
 *
 * 준비 중 상태를 나타내는 아이콘과 텍스트를 포함합니다.
 *
 */

@Composable
fun PrepareScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        EmptyImage(
            modifier = Modifier.padding(top = 278.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrepareScreenBottomBarPreview() {
    PrepareScreen()
}
