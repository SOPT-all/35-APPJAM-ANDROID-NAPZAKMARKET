package com.napzak.market.presentation.explore.explore.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.TradeType

@Composable
fun TradeTypeTab(
    selectedTab: TradeType,
    onTradeTypeClick: (TradeType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor = NapzakMarketTheme.colors.gray300

    Row(
        modifier = modifier
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val strokeHeightPoint = size.height - 1
                drawLine(
                    color = borderColor,
                    start = Offset(0f, strokeHeightPoint),
                    end = Offset(size.width, strokeHeightPoint),
                    strokeWidth = strokeWidth,
                )
            }
            .padding(horizontal = 20.dp),
    ) {
        TradeTypeTabItem(
            tradeType = TradeType.SELL.label,
            isSelected = selectedTab == TradeType.SELL,
            onTradeTypeClick = { onTradeTypeClick(TradeType.SELL) },
            modifier = Modifier.weight(1f),
        )

        Spacer(Modifier.width(15.dp))

        TradeTypeTabItem(
            tradeType = TradeType.BUY.label,
            isSelected = selectedTab == TradeType.BUY,
            onTradeTypeClick = { onTradeTypeClick(TradeType.BUY) },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun TradeTypeTabItem(
    tradeType: String,
    isSelected: Boolean,
    onTradeTypeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor =
        if (isSelected) NapzakMarketTheme.colors.purple30 else NapzakMarketTheme.colors.gray300
    val textColor =
        if (isSelected) NapzakMarketTheme.colors.purple30 else NapzakMarketTheme.colors.gray600
    val textStyle =
        if (isSelected) NapzakMarketTheme.typography.bodySemi16 else NapzakMarketTheme.typography.bodyMedium16

    Column(
        modifier = modifier
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val strokeHeightPoint = size.height - 1
                drawLine(
                    color = borderColor,
                    start = Offset(0f, strokeHeightPoint),
                    end = Offset(size.width, strokeHeightPoint),
                    strokeWidth = strokeWidth,
                )
            }
            .noRippleClickable { onTradeTypeClick() }
            .padding(bottom = 2.dp)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = tradeType,
            style = textStyle,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TradeTypeTabPreview(modifier: Modifier = Modifier) {
    TradeTypeTab(
        selectedTab = TradeType.SELL,
        onTradeTypeClick = { /* 특정 탭이 클릭 되었을 때 */ },
        modifier = modifier,
    )
}