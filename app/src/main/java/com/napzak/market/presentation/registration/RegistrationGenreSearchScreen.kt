package com.napzak.market.presentation.registration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.R.string.genre_search_genre_example
import com.napzak.market.R.string.search_genre
import com.napzak.market.core.designsystem.component.item.GenreSearchItem
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.core.designsystem.component.topbar.BackTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre

@Composable
fun RegistrationGenreSearchRoute(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchTerm by viewModel.searchTerm.collectAsStateWithLifecycle()

//    DisposableEffect(Unit) {
//        onDispose {
//            viewModel.updateSearchTerm(BLANK)
//        }
//    }
    LaunchedEffect(true) {
        viewModel.debounce()
    }

    RegistrationGenreSearchScreen(
        onBackClick = navigateUp,
        genreList = uiState.genreList,
        searchValue = searchTerm,
        onSearchValueChange = viewModel::updateSearchTerm,
        onSearchButtonClick = viewModel::searchGenre,
        onGenreSelect = {
            viewModel.updateGenre(it.genreName)
//            viewModel.updateSearchTerm(BLANK)
            navigateUp()
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegistrationGenreSearchScreen(
    onBackClick: () -> Unit,
    genreList: List<Genre>,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
    onGenreSelect: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    val paddedModifier = Modifier.padding(horizontal = 20.dp)

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.white),
    ) {
        LazyColumn(
            modifier = modifier
                .background(NapzakMarketTheme.colors.white),
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier.background(color = NapzakMarketTheme.colors.white)
                ) {
                    BackTopBar(
                        title = stringResource(search_genre),
                        onBackClick = onBackClick,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SearchBox(
                        modifier = paddedModifier,
                        searchTerm = searchValue,
                        placeholder = stringResource(genre_search_genre_example),
                        onTextChange = onSearchValueChange,
                        onSearchButtonClick = onSearchButtonClick,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            itemsIndexed(
                items = genreList,
                key = { _, item -> item }
            ) { index, genre ->
//            Text(
//                modifier = paddedModifier
//                    .fillMaxWidth()
//                    .padding(vertical = 20.dp)
//                    .noRippleClickable { onGenreSelect(genre) },
//                text = genre.genreName,
//                style = NapzakMarketTheme.typography.bodySemi14,
//                color = NapzakMarketTheme.colors.gray800,
//            )
//            if (index != genreList.lastIndex) {
//                HorizontalDivider(
//                    modifier = paddedModifier,
//                    color = NapzakMarketTheme.colors.gray100,
//                    thickness = 10.dp,
//                )
//            }
                GenreSearchItem(
                    genreName = genre.genreName,
                    onGenreItemClick = { onGenreSelect(genre) },
                    isLastItem = index == genreList.size - 1,
                    modifier = paddedModifier,
                )
            }
        }
    }
}

//private const val BLANK = ""

@Preview
@Composable
private fun RegistrationGenreSearchScreenPreview() {
    NapzakMarketTheme {
        RegistrationGenreSearchScreen(
            genreList = emptyList(),
            onBackClick = {},
            searchValue = "",
            onSearchValueChange = {},
            onSearchButtonClick = {},
            onGenreSelect = {},
        )
    }
}
