package com.napzak.market.presentation.chat.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
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
) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.img_chat_ready),
                contentDescription = stringResource(R.string.loading_ready_image_description),
                tint = Color.Unspecified,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.loading_title_description),
                style = NapzakMarketTheme.typography.titleSemi20,
                color = NapzakMarketTheme.colors.gray800,
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(id = R.string.loading_subtitle_description),
                style = NapzakMarketTheme.typography.bodyMedium14,
                color = NapzakMarketTheme.colors.gray600,
            )
        }
    }

@Preview(showBackground = true)
@Composable
fun ChatScreenBottomBarPreview() {
    ChatScreen()
}
