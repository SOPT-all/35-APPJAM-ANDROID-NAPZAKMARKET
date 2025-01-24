package com.napzak.market.presentation.explore.search.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.item.GenreSearchItem
import com.napzak.market.domain.genre.model.Genre
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchGenreListSection(
    genreItems: List<Genre>,
    onGenreItemClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isClickable by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        itemsIndexed(genreItems) { index, genreItem ->
            GenreSearchItem(
                genreName = genreItem.genreName,
                onGenreItemClick = {
                    if (isClickable) {
                        isClickable = false
                        coroutineScope.launch {
                            delay(100L)
                            onGenreItemClick(genreItem)
                            isClickable = true
                        }
                    }
                },
                isGenreChipVisible = true,
                isLastItem = index == genreItems.size - 1,
            )
        }
    }
}

@Preview
@Composable
private fun SearchGenreListSectionPreview(modifier: Modifier = Modifier) {
    SearchGenreListSection(
        genreItems = emptyList(),
        onGenreItemClick = { },
        modifier = modifier
    )
}
