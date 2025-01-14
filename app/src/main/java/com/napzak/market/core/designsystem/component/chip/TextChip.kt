package com.napzak.market.core.designsystem.component.chip

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 텍스트만 포함하는 칩 컴포넌트
 *
 * @param text 텍스트
 * @param textStyle 텍스트 스타일
 * @param modifier 수정자
 * @param chipColors 칩의 색상과 테두리 색상을 정의하는 CustomChipColors 객체
 * @param innerPadding 내부 패딩
 * @param shape 칩의 모양
 */

@Composable
fun TextChip(
    text: String,
    textStyle: TextStyle = NapzakMarketTheme.typography.capSemi12,
    modifier: Modifier = Modifier,
    chipColors: CustomChipColors = CustomChipColors(),
    innerPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
    shape: Shape = RectangleShape,
) {
    BasicChip(
        text = text,
        textStyle = textStyle,
        chipColors = chipColors,
        shape = shape,
        borderWidth = 0.dp,
        innerPaddingValues = innerPadding,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun SellTextChipPreview() {
    NapzakMarketTheme {
        TextChip(
            text = "팔아요",
            chipColors = CustomChipColors(
                contentColor = NapzakMarketTheme.colors.white,
                containerColor = NapzakMarketTheme.colors.purple30,
            )
        )
    }
}

@Preview
@Composable
private fun BuyTextChipPreview() {
    NapzakMarketTheme {
        TextChip(
            text = "구해요",
            chipColors = CustomChipColors(
                contentColor = NapzakMarketTheme.colors.white,
                containerColor = NapzakMarketTheme.colors.gray900,
            )
        )
    }
}