package com.napzak.market.core.designsystem.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 기본 텍스트 버튼 컴포넌트
 *
 * @param text 버튼에 표시될 텍스트
 * @param onClick 버튼 클릭 시 수행할 동작
 * @param width 버튼의 너비
 * @param height 버튼의 높이
 * @param shape 버튼의 모서리 모양
 * @param textStyle 버튼 텍스트의 스타일
 * @param isEnabled 버튼 활성화 여부
 * @param backgroundColor 버튼의 배경색
 * @param contentColor 버튼 텍스트 색상
 */

@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    shape: RoundedCornerShape,
    textStyle: TextStyle,
    isEnabled: Boolean = true,
    backgroundColor: Color,
    contentColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) backgroundColor else NapzakMarketTheme.colors.gray400,
            contentColor = contentColor
        ),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = textStyle.copy(
                color = if (isEnabled) contentColor else NapzakMarketTheme.colors.white
            )
        )
    }
}