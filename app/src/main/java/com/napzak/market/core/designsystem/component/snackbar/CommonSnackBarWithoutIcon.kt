package com.napzak.market.core.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 공통 SnackBar 컴포넌트 (아이콘 없음).
 *
 * 메시지만 포함한 스낵바 메시지를 표시합니다.
 * 사용자 지정 배경색, 텍스트 색상, 텍스트 스타일, 모양 등을 지원하며, 모든 인자는 명시적으로 전달해야 합니다.
 *
 * @param message 표시할 메시지
 * @param backgroundColor 배경색
 * @param textColor 텍스트 색상
 * @param textStyle 텍스트 스타일
 * @param shape 모양
 * @param modifier Modifier
 */
@Composable
fun CommonSnackBarWithoutIcon(
    message: String,
    backgroundColor: Color,
    textColor: Color,
    textStyle: TextStyle,
    shape: Shape,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape,
            )
            .padding(
                horizontal = 20.dp,
                vertical = 12.dp,
            ),
    ) {
        Text(
            text = message,
            style = textStyle,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommonSnackBarWithoutIconPreview() {
    NapzakMarketTheme {
        CommonSnackBarWithoutIcon(
            message = "버튼을 한번 더 누르시면 종료 돼요.",
            backgroundColor = NapzakMarketTheme.colors.black70,
            textColor = NapzakMarketTheme.colors.white,
            textStyle = NapzakMarketTheme.typography.bodyMedium14,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(16.dp)
                .width(320.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommonSnackBarWithoutIconMessagePreview() {
    NapzakMarketTheme {
        CommonSnackBarWithoutIcon(
            message = "긴 메시지가 들어갑니다. 메시지가 길어져도 문제가 없는지 확인하기 위한 예제입니다.",
            backgroundColor = NapzakMarketTheme.colors.black70,
            textColor = NapzakMarketTheme.colors.white,
            textStyle = NapzakMarketTheme.typography.bodyMedium14,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(16.dp)
                .width(320.dp)
        )
    }
}
