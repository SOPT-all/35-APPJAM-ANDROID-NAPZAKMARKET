package com.napzak.market.presentation.search.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchGenreListSection(
    genreList: List<String>, //추후 타입 변경 필요
    onGenreItemClick: (String) -> Unit, //타입 장르로 변경
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        itemsIndexed(genreList) { index, genreItem ->
            if (index == genreList.size-1) {
                SearchGenreItem(
                    genreName = genreItem,
                    onGenreItemClick = { onGenreItemClick(genreItem)},
                    isLastItem = true,
                )
            } else {
                SearchGenreItem(
                    genreName = genreItem,
                    onGenreItemClick = { onGenreItemClick(genreItem)},
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchGenreListSectionPreview(modifier: Modifier = Modifier) {
    SearchGenreListSection(
        genreList = emptyList(),
        onGenreItemClick = { },
        modifier = modifier
    )
}
