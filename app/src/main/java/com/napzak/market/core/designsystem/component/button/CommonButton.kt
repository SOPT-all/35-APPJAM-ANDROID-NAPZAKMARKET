package com.napzak.market.core.designsystem.component.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.napzak.market.core.common.util.NoRippleInteractionSource

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
    backgroundColor: Color,
    contentColor: Color,
    shape: RoundedCornerShape,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) backgroundColor else NapzakMarketTheme.colors.gray400,
            contentColor = if (isEnabled) contentColor else NapzakMarketTheme.colors.white
        ),
        enabled = isEnabled,
        interactionSource = NoRippleInteractionSource,
        content = {
            Text(
                text = text,
                style = textStyle
            )
        }
    )
}
