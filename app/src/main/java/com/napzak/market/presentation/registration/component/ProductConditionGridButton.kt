package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme


/**
 * Product condition grid button
 *
 * 상품의 상태를 표시하는 버튼으로, 초기 상태는 비어 있음
 *
 * @param conditionType
 * @param selectedIndex
 * @param onIndexSelected
 * @param modifier
 */

@Composable
fun ProductConditionGridButton(
    conditionType: List<String>,
    selectedIndex: Int,
    onIndexSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        conditionType.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEachIndexed { _, item ->
                    val index = conditionType.indexOf(item)
                    ProductConditionItem(
                        modifier = Modifier
                            .weight(1f)
                            .noRippleClickable { onIndexSelected(index) },
                        condition = item,
                        isSelected = selectedIndex == index
                    )
                }
            }
        }
    }
}

@Composable
fun ProductConditionItem(
    condition: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val napzakMarketColors = NapzakMarketTheme.colors
    val borderColor = if (isSelected) napzakMarketColors.gray900 else napzakMarketColors.gray200
    val backgroundColor = if (isSelected) napzakMarketColors.gray900 else napzakMarketColors.white
    val textColor = if (isSelected) napzakMarketColors.white else napzakMarketColors.gray900

    Box(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = condition,
            style = NapzakMarketTheme.typography.bodyMedium14,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun ProductConditionGridButtonPreview() {
    NapzakMarketTheme {
        var selectedIndex by remember { mutableStateOf(-1) }
        ProductConditionGridButton(
            conditionType = listOf("미개봉", "아주 좋은 상태", "약간의 사용감", "사용감"),
            selectedIndex = selectedIndex,
            onIndexSelected = { index -> selectedIndex = index }
        )
    }
}
