package com.napzak.market.core.designsystem.component.item

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
import com.napzak.market.presentation.search.component.GenreTextChip

/**
 * 장르 검색 시 하단에 보여지는 장르 item 컴포넌트
 *
 * @param genreName 장르 이름
 * @param onGenreItemClick 장르 item을 클릭 시 실행되는 콜백
 * @param modifier 수정자
 * @param isGenreChipVisible 장르 Chip 보여짐 여부
 * @param isLastItem 리스트의 마지막 아이템인지 알려주는 값
 */
@Composable
fun GenreSearchItem(
    genreName: String,
    onGenreItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    isGenreChipVisible: Boolean = false,
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
        if (isGenreChipVisible) {
            GenreTextChip()

            Spacer(Modifier.width(8.dp))
        }

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
        GenreSearchItem(
            genreName = "짱구는 못말려",
            onGenreItemClick = { },
            modifier = modifier,
        )
        GenreSearchItem(
            genreName = "짱구는 못말려",
            onGenreItemClick = { },
            modifier = modifier,
            isLastItem = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchGenreItemPreview2(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.white)
    ) {
        GenreSearchItem(
            genreName = "짱구는 못말려",
            isGenreChipVisible = true,
            onGenreItemClick = { },
            modifier = modifier,
        )
        GenreSearchItem(
            genreName = "짱구는 못말려",
            onGenreItemClick = { },
            isGenreChipVisible = true,
            modifier = modifier,
            isLastItem = true
        )
    }
}