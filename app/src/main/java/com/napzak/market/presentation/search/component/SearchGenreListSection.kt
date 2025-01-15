package com.napzak.market.presentation.search.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.domain.genre.model.Genre

@Composable
fun SearchGenreListSection(
    genreList: List<Genre>,
    onGenreItemClick: (Genre) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        itemsIndexed(genreList) { index, genreItem ->
            if (index == genreList.size-1) {
                SearchGenreItem(
                    genreName = genreItem.genreName,
                    onGenreItemClick = { onGenreItemClick(genreItem)},
                    isLastItem = true,
                )
            } else {
                SearchGenreItem(
                    genreName = genreItem.genreName,
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
