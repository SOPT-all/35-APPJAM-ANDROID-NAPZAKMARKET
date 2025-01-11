package com.napzak.market.core.designsystem.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 기본 텍스트 버튼 컴포넌트
 *
 * @param text 버튼에 표시될 텍스트
 * @param onClick 버튼 클릭 시 수행할 동작
 */

@Composable
fun BasicTextButton(
    text: String,
    onClick: () -> Unit
) {
    CommonButton(
        text = text,
        onClick = onClick,
        width = 335.dp,
        height = 52.dp,
        shape = RoundedCornerShape(12.dp),
        textStyle = NapzakMarketTheme.typography.bodyBold16,
        backgroundColor = NapzakMarketTheme.colors.black,
        contentColor = NapzakMarketTheme.colors.white
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun BasicTextButtonPreview() {
    NapzakMarketTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BasicTextButton(
                text = "납작마켓 시작하기",
                onClick = { /* Preview Action */ }
            )
        }
    }
}