package com.napzak.market.presentation.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun SearchGenreItem(
    genreName: String,
    onGenreItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLastItem: Boolean = false,
) {
    val borderColor =
        if (isLastItem) NapzakMarketTheme.colors.white else NapzakMarketTheme.colors.gray100

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(NapzakMarketTheme.colors.white)
            .noRippleClickable(onGenreItemClick)
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height - 1),
                    end = Offset(size.width, size.height - 1),
                    strokeWidth = 1.dp.toPx(),
                )
            }
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GenreTextChip()

        Spacer(Modifier.width(8.dp))

        Text(
            text = genreName,
            style = NapzakMarketTheme.typography.bodySemi14,
            color = NapzakMarketTheme.colors.gray800,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchGenreItemPreview(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.white)
    ) {
        SearchGenreItem(
            genreName = "짱구는 못말려",
            onGenreItemClick = { },
            modifier = modifier,
        )
        SearchGenreItem(
            genreName = "짱구는 못말려",
            onGenreItemClick = { },
            modifier = modifier,
            isLastItem = true
        )
    }
}