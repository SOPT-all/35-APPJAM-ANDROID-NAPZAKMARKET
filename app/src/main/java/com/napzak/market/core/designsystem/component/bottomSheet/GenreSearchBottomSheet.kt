package com.napzak.market.core.designsystem.component.bottomSheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.component.GenreChipButtonGroup
import com.napzak.market.core.designsystem.component.button.EnableDisableTextButton
import com.napzak.market.core.designsystem.component.item.GenreSearchItem
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.explore.component.GenreSearchNoticeSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreSearchBottomSheet(
    initialSelectedGenreList: List<Genre>,
    genreItems: UiState<List<Genre>>,
    debounce: () -> Unit,
    onDismissRequest: () -> Unit,
    onTextChange: (String) -> Unit,
    onButtonClick: (List<Genre>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    var searchTerm by remember { mutableStateOf("") }
    var selectedGenreList by remember {
        mutableStateOf<List<Genre>>(
            initialSelectedGenreList
        )
    }

    LaunchedEffect(true) {
        debounce()
    }

    LaunchedEffect(searchTerm) {
        onTextChange(searchTerm)
    }

    BackHandler(
        enabled = searchTerm.isNotBlank(),
    ) {
        searchTerm = ""
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.black70)
            .noRippleClickable {
                focusManager.clearFocus()
                onDismissRequest()
            },
        verticalArrangement = Arrangement.Bottom,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(NapzakMarketTheme.colors.white)
                .noRippleClickable { /* 클릭 방지 */ }
                .padding(top = 30.dp),
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
                modifier = Modifier.padding(horizontal = 20.dp),
            )

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                when (genreItems) {
                    is UiState.Loading -> {}
                    is UiState.Empty -> {}
                    is UiState.Failure -> {}
                    is UiState.Success -> {
                        with(genreItems) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                            ) {
                                itemsIndexed(
                                    items = data,
                                    key = { _, genreItem -> genreItem.genreId },
                                ) { index, genreItem ->
                                    GenreSearchItem(
                                        genreName = genreItem.genreName,
                                        onGenreItemClick = {
                                            if (selectedGenreList.size < MAX_GENRE_SELECTION) {
                                                selectedGenreList = selectedGenreList + genreItem
                                            }
                                            focusManager.clearFocus()
                                        },
                                        isLastItem = index == selectedGenreList.size - 1,
                                    )
                                }

                                item {
                                    if (selectedGenreList.isNotEmpty()) {
                                        Spacer(Modifier.height(58.dp))
                                    }
                                }
                            }
                        }
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
                                backgroundColor = NapzakMarketTheme.colors.gray50,
                                contentPaddingValues = PaddingValues(end = 20.dp),
                                modifier = Modifier
                                    .padding(start = 20.dp, top = 16.dp)
                                    .zIndex(1f),
                            )
                        }
                    }
                }
            }
            EnableDisableTextButton(
                text = stringResource(R.string.genre_search_apply_button),
                isEnabled = true,
                onClick = { onButtonClick(selectedGenreList) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            )
        }
    }
}

private const val MAX_GENRE_SELECTION = 4
