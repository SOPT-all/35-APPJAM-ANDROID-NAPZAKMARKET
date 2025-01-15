package com.napzak.market.presentation.explore.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.chip.SelectableChip
import com.napzak.market.core.designsystem.component.chip.type.SelectableChipType
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.type.TradeType

@Composable
fun ExploreFilterGroup(
    tradeType: String,
    genreList: List<Genre>,
    isOnSale: Boolean,
    isUnopened: Boolean,
    onGenreListClick: () -> Unit,
    onSoldOutClick: () -> Unit,
    onUnopenClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(color = NapzakMarketTheme.colors.gray50)
            .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        SelectableChip(
            isSelected = genreList.isNotEmpty(),
            modifier = modifier,
            selectableChipType = SelectableChipType.GENRE,
            onClick = onGenreListClick,
            genreList = genreList,
        )

        Spacer(Modifier.width(6.dp))

        SelectableChip(
            isSelected = isOnSale,
            modifier = modifier,
            selectableChipType = SelectableChipType.SOLD_OUT,
            onClick = onSoldOutClick,
            genreList = genreList,
        )

        if (tradeType == TradeType.SELL.name) {
            Spacer(Modifier.width(6.dp))

            SelectableChip(
                isSelected = isUnopened,
                modifier = modifier,
                selectableChipType = SelectableChipType.UNOPEN,
                onClick = onUnopenClick,
                genreList = genreList,
            )
        }
    }
}

@Preview
@Composable
private fun FilterGroupPreview(modifier: Modifier = Modifier) {
    ExploreFilterGroup(
        tradeType = TradeType.SELL.name,
        genreList = emptyList(),
        isOnSale = false,
        isUnopened = false,
        onGenreListClick = { /* 장르 검색 bottomSheet 연결 */ },
        onSoldOutClick = { /* 품절제외 클릭 */ },
        onUnopenClick = { /* 미개봉 클릭 */ },
        modifier = modifier,
    )
}