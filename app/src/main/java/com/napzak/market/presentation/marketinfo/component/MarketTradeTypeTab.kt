package com.napzak.market.presentation.marketinfo.component

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.MarketTab

/**
 * 상품 거래 타입을 선택하는 탭 컴포넌트
 *
 * @param itemPadding 탭 사이에 적용될 패딩
 * @param selectedTab 선택된 탭
 * @param onTradeTypeClick 탭이 클릭 되었을 경우 실행되는 콜백
 * @param modifier 수정자
 */

@Composable
fun MarketTradeTypeTab(
    itemPadding: Dp,
    selectedTab: MarketTab,
    onTradeTypeClick: (MarketTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor = NapzakMarketTheme.colors.gray300
    val marketTabList = listOf(MarketTab.SELL, MarketTab.BUY, MarketTab.REVIEW)

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
        marketTabList.forEachIndexed { index, tradeTypeItem ->
            TradeTypeTabItem(
                tradeType = tradeTypeItem.label,
                isSelected = selectedTab == tradeTypeItem,
                onTradeTypeClick = { onTradeTypeClick(tradeTypeItem) },
                modifier = Modifier.weight(1f),
            )

            if (index != marketTabList.lastIndex) {
                Spacer(Modifier.width(itemPadding))
            }
        }
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
            .noRippleClickable(onTradeTypeClick)
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
    MarketTradeTypeTab(
        itemPadding = 12.dp,
        selectedTab = MarketTab.SELL,
        onTradeTypeClick = { /* 특정 탭이 클릭 되었을 때 */ },
        modifier = modifier,
    )
}