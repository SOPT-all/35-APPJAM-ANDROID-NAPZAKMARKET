package com.napzak.market.core.designsystem.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 활성화/비활성화 버튼 컴포넌트
 *
 * @param text 버튼에 표시될 텍스트
 * @param isEnabled 버튼 활성화 여부
 * @param onClick 버튼 클릭 시 수행할 동작
 */

@Composable
fun EnableDisableTextButton(
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    textStyle: TextStyle = NapzakMarketTheme.typography.bodyBold16,
    backgroundColor: Color = NapzakMarketTheme.colors.purple30,
    contentColor: Color = NapzakMarketTheme.colors.white,
    modifier: Modifier = Modifier
) {
    CommonButton(
        text = text,
        onClick = { if (isEnabled) onClick() },
        isEnabled = isEnabled,
        modifier = modifier,
        shape = shape,
        textStyle = textStyle,
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = NapzakMarketTheme.colors.gray400,
            disabledContentColor = NapzakMarketTheme.colors.white
        )
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EnableDisableTextButtonPreview() {
    NapzakMarketTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EnableDisableTextButton(
                text = "선택 완료! 시작하기",
                isEnabled = true,
                onClick = { /* Preview Action */ },
                modifier = Modifier.height(52.dp).width(335.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            EnableDisableTextButton(
                text = "선택 완료! 시작하기",
                isEnabled = false,
                onClick = { /* Preview Action */ },
                modifier = Modifier.height(52.dp).width(335.dp)
            )
        }
    }
}
