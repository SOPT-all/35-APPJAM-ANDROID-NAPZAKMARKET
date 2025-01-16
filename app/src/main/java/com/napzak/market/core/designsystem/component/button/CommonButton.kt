package com.napzak.market.core.designsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.napzak.market.core.common.util.NoRippleInteractionSource

/**
 * 기본 텍스트 버튼 컴포넌트
 *
 * @param text 버튼에 표시될 텍스트
 * @param onClick 버튼 클릭 시 수행할 동작
 * @param shape 버튼의 모서리 모양
 * @param textStyle 버튼 텍스트의 스타일
 * @param isEnabled 버튼 활성화 여부
 */

@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    buttonColors: ButtonColors,
    shape: RoundedCornerShape,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp),
    isEnabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = buttonColors,
        enabled = isEnabled,
        contentPadding = contentPadding,
        interactionSource = NoRippleInteractionSource,
        content = {
            Text(
                text = text,
                style = textStyle,
            )
        }
    )
}
