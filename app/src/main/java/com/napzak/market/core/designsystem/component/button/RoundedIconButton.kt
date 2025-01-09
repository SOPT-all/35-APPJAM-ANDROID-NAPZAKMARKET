package com.napzak.market.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.button.model.CustomButtonColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 동그란 모양의 아이콘 버튼을 나타내는 컴포넌트
 *
 * @param icon 표시할 아이콘
 * @param onClick 버튼 클릭 시 실행할 콜백
 * @param modifier 수정자
 * @param colors 버튼의 색상과 테두리 색상을 정의하는 CustomButtonColors 객체
 * @param innerPadding 내부 패딩값
 */

@Composable
fun RoundedIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: CustomButtonColors = CustomButtonColors(),
    innerPadding: PaddingValues = PaddingValues(8.dp),
) {
    CompositionLocalProvider(
        value = LocalContentColor provides colors.contentColor(),
        content = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = modifier
                    .clickable(
                        role = Role.Button,
                        onClick = onClick
                    )
                    .background(color = colors.containerColor(), shape = CircleShape)
                    .border(width = 1.dp, color = colors.borderColor(), shape = CircleShape)
                    .padding(innerPadding),
            )
        }
    )
}

@Preview
@Composable
private fun RoundedIconButtonPreview() {
    NapzakMarketTheme {
        RoundedIconButton(
            icon = ImageVector.vectorResource(id = R.drawable.ic_reset_18),
            onClick = {},
        )
    }
}