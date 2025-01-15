package com.napzak.market.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.search.state.SearchUiState
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.search.component.SearchGenreListSection
import com.napzak.market.R.string.left_chevron_button
import com.napzak.market.R.string.explore_search_box_placeholder

@Composable
fun SearchRoute(
    initSearchTerm: String?,
    navigateToExplore: (String?, Long?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchTerm by viewModel.searchTerm.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        if (initSearchTerm != null) {
            viewModel.updateSearchValue(initSearchTerm)
            viewModel.getGenreList(initSearchTerm)
        } else {
            viewModel.getGenreList("")
        }
    }

    SearchScreen(
        modifier = modifier,
        uiState = uiState,
        searchTerm = searchTerm,
        onBackButtonClick = { navigateToExplore(null, null) },
        onTextChange = viewModel::changeSearchText,
        onSearchButtonClick = { navigateToExplore(searchTerm, null) },
        onGenreItemClick = { genreItem ->
            navigateToExplore(genreItem.genreName, genreItem.genreId)
        },
    )

}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    searchTerm: String,
    onBackButtonClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
    onGenreItemClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState.loadState) {
        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Success -> {
            with(uiState.loadState.data) {
                SearchSuccessScreen(
                    modifier = modifier,
                    searchTerm = searchTerm,
                    genreList = genreList,
                    onBackButtonClick = onBackButtonClick,
                    onTextChange = onTextChange,
                    onSearchButtonClick = onSearchButtonClick,
                    onGenreItemClick = onGenreItemClick,
                )
            }
        }
    }
}

@Composable
fun SearchSuccessScreen(
    searchTerm: String,
    genreList: List<Genre>,
    onBackButtonClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
    onGenreItemClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler(
        enabled = searchTerm.isNotBlank()
    ) {
        onTextChange("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NapzakMarketTheme.colors.white)
            .padding(top = 40.dp),
    ) {
        Row(
            modifier = Modifier.padding(end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .noRippleClickable(onBackButtonClick),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back_24),
                    contentDescription = stringResource(left_chevron_button),
                    tint = Color.Unspecified,
                )
            }
            SearchBox(
                placeholder = stringResource(explore_search_box_placeholder),
                searchTerm = searchTerm,
                onTextChange = onTextChange,
                onSearchButtonClick = onSearchButtonClick,
            )
        }

        Spacer(Modifier.height(20.dp))

        SearchGenreListSection(
            genreList = genreList,
            onGenreItemClick = onGenreItemClick,
        )
    }
}

@Preview
@Composable
private fun SearchSuccessScreenPreview(modifier: Modifier = Modifier) {
    SearchSuccessScreen(
        searchTerm = "",
        genreList = emptyList(),
        onBackButtonClick = { },
        onTextChange = { },
        onSearchButtonClick = { },
        onGenreItemClick = { },
        modifier = modifier
    )
}