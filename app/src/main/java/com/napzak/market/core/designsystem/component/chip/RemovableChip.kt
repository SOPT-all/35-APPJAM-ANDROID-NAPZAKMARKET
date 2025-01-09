package com.napzak.market.core.designsystem.component.chip

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 삭제 가능한 칩을 나타내는 컴포넌트
 *
 * @param text 칩에 표시할 텍스트
 * @param onClick 칩을 클릭할 때 실행할 콜백
 * @param modifier 수정자
 * @param textStyle [text]의 글꼴
 * @param chipColors 칩의 색상과 테두리 색상을 정의하는 CustomChipColors 객체
 */

@Composable
fun RemovableChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = NapzakMarketTheme.typography.bodyBold14,
    chipColors: CustomChipColors = CustomChipColors(),
) {
    BasicChip(
        text = text,
        textStyle = textStyle,
        shape = RoundedCornerShape(50),
        chipColors = chipColors,
        borderWidth = 1.dp,
        innerPaddingValues = PaddingValues(
            horizontal = 12.dp,
            vertical = 7.dp
        ),
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_close_18),
                contentDescription = null
            )
        },
        modifier = modifier,
        onClick = onClick,

    )
}

@Preview
@Composable
private fun RemovableChipPreview() {
    NapzakMarketTheme {
        RemovableChip(text = "실바니안", onClick = {})
    }
}