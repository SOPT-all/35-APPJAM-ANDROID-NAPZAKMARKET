package com.napzak.market.presentation.explore.bottomSheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetDefaults.DragHandle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.textField.SearchBox
import androidx.compose.runtime.*
import com.napzak.market.core.designsystem.component.GenreChipButtonGroup
import com.napzak.market.core.designsystem.component.button.EnableDisableTextButton
import com.napzak.market.core.designsystem.component.item.GenreSearchItem
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.component.GenreSearchNoticeSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreSearchBottomSheet(
    initialSelectedGenreList: List<Genre>,
    initialGenreList: List<Genre>,
    genreList: List<Genre>,
    onDismissRequest: () -> Unit,
    onTextChange: (String) -> Unit,
    onButtonClick: (List<Genre>) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = NapzakMarketTheme.colors.white,
        scrimColor = NapzakMarketTheme.colors.black70,
        dragHandle = {
            DragHandle(
                color = NapzakMarketTheme.colors.gray200,
                width = 36.dp,
                height = 4.dp,
            )
        },
        modifier = Modifier.wrapContentHeight()
    ) {
        var searchTerm by remember { mutableStateOf("") }
        var selectedGenreList by remember { mutableStateOf<List<Genre>>(initialSelectedGenreList) }

        LaunchedEffect(searchTerm) {
            onTextChange(searchTerm)
        }

        BackHandler(
            enabled = searchTerm.isNotBlank()
        ) {
            searchTerm = ""
        }

        Column(
            modifier = Modifier
                .background(NapzakMarketTheme.colors.white)
        ) {
            GenreSearchNoticeSection()

            Spacer(Modifier.height(18.dp))

            SearchBox(
                placeholder = stringResource(R.string.genre_search_genre_example),
                searchTerm = searchTerm,
                onTextChange = {
                    searchTerm = it
                    onTextChange(it)
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(345.dp),
            ) {
                val list = if (searchTerm.isEmpty()) initialGenreList else genreList

                LazyColumn(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    itemsIndexed(
                        items = list,
                        key = { _, genreItem -> genreItem.genreId }
                    ) { index, genreItem ->
                        GenreSearchItem(
                            genreName = genreItem.genreName,
                            onGenreItemClick = {
                                if (selectedGenreList.size < 4) {
                                    selectedGenreList = selectedGenreList + genreItem
                                }
                            },
                            isLastItem = index == selectedGenreList.size - 1
                        )
                    }
                }

                if (selectedGenreList.isNotEmpty()) {
                    Column {
                        Spacer(Modifier.weight(1f))
                        Row(
                            modifier = Modifier
                                .background(NapzakMarketTheme.colors.gray50)
                                .padding(bottom = 12.dp),
                        ) {
                            GenreChipButtonGroup(
                                genreList = selectedGenreList,
                                onGenreClick = { selectedGenre ->
                                    selectedGenreList =
                                        selectedGenreList.filter { it.genreId != selectedGenre.genreId }
                                },
                                onResetClick = { selectedGenreList = emptyList() },
                                contentPaddingValues = PaddingValues(horizontal = 20.dp),
                                modifier = Modifier
                                    .padding(top = 16.dp),
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            EnableDisableTextButton(
                text = setButtonText(selectedGenreList),
                isEnabled = isButtonActivate(initialSelectedGenreList, selectedGenreList),
                onClick = { onButtonClick(selectedGenreList) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            Spacer(Modifier.height(35.dp))
        }
    }
}

private fun isButtonActivate(
    initialSelectedGenreList: List<Genre>,
    selectedGenreList: List<Genre>,
): Boolean {
    return selectedGenreList.isNotEmpty() && !((initialSelectedGenreList.size == selectedGenreList.size) && initialSelectedGenreList.containsAll(
        selectedGenreList
    ))
}

@Composable
private fun setButtonText(
    selectedGenreList: List<Genre>,
): String {
    return if (selectedGenreList.isEmpty()) stringResource(R.string.genre_search_apply_button) else {
        stringResource(
            R.string.explore_product_count,
            selectedGenreList.size
        ) + stringResource(R.string.genre_search_apply_button)
    }
}