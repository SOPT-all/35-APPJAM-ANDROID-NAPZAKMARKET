package com.napzak.market.core.designsystem.component.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun PageIndicator(
    imageCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    selectedColor: Color = NapzakMarketTheme.colors.purple30,
    unselectedColor: Color = NapzakMarketTheme.colors.gray400,
) {
    Row(
        modifier = modifier
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        repeat(imageCount) { iteration ->
            val color =
                if (pagerState.currentPage % imageCount == iteration) selectedColor else unselectedColor
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(color),
            )
        }
    }
}

@Preview
@Composable
private fun PageIndicatorPreview() {
    NapzakMarketTheme {
        PageIndicator(
            imageCount = 5,
            pagerState = rememberPagerState(initialPage = 1, pageCount = { 3 }),
        )
    }
}
