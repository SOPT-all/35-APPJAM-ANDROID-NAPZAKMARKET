package com.napzak.market.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.Black
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 기본 칩 컴포넌트입니다.
 *
 * @param text 칩에 표시할 텍스트
 * @param chipColors 칩의 색상과 테두리 색상을 정의하는 CustomChipColors 객체
 * @param modifier 수정자
 * @param borderWidth 테두리의 두께
 * @param shape 칩의 모양
 * @param textStyle [text]의 글꼴
 * @param leadingIcon 왼쪽에 표시할 아이콘, null이면 표시되지 않음
 * @param trailingIcon 오른쪽에 표시할 아이콘, null이면 표시되지 않음
 * @param innerPaddingValues 내부 패딩값
 */

@Composable
fun BasicChip(
    text: String,
    chipColors: CustomChipColors,
    modifier: Modifier = Modifier,
    borderWidth: Dp,
    shape: Shape,
    textStyle: TextStyle,
    innerPaddingValues: PaddingValues,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(shape)
            .background(
                color = chipColors.containerColor(),
            )
            .then(  // border가 0이 아닐 때만 border를 적용
                Modifier.border(
                    width = borderWidth,
                    color = chipColors.borderColor(),
                    shape = shape
                ).takeIf { borderWidth > 0.dp } ?: Modifier
            )
            .clickable { onClick() }
            .padding(innerPaddingValues),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (leadingIcon != null) {
            CompositionLocalProvider(
                LocalContentColor provides chipColors.leadingIconColor(),
                content = leadingIcon
            )
        }

        CompositionLocalProvider(
            LocalContentColor provides chipColors.contentColor(),
            content = {
                Text(
                    text = text,
                    style = textStyle,
                )
            }
        )



        if (trailingIcon != null) {
            CompositionLocalProvider(
                LocalContentColor provides chipColors.trailingIconColor(),
                content = trailingIcon
            )
        }
    }
}

@Preview
@Composable
private fun BasicChipPreview() {
    NapzakMarketTheme {
        BasicChip(
            text = "실바니안",
            shape = RoundedCornerShape(50),
            borderWidth = 1.dp,
            textStyle = NapzakMarketTheme.typography.bodyBold14,
            chipColors = CustomChipColors(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            innerPaddingValues = PaddingValues(
                horizontal = 12.dp,
                vertical = 7.dp
            ),
        )
    }
}