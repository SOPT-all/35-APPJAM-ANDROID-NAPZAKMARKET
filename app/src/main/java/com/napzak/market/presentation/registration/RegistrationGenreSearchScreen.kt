package com.napzak.market.presentation.registration

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.R.string.genre_search_genre_example
import com.napzak.market.R.string.search_genre
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.core.designsystem.component.topbar.BackTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun RegistrationGenreSearchRoute(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.updateSearchTerm(BLANK)
        }
    }

    RegistrationGenreSearchScreen(
        onBackClick = navigateUp,
        genreList = uiState.genreList,
        searchValue = uiState.searchTerm,
        onSearchValueChange = viewModel::updateSearchTerm,
        onSearchButtonClick = viewModel::searchGenre,
        onGenreSelect = {
            viewModel.updateGenre(it)
            viewModel.updateSearchTerm(BLANK)
            navigateUp()
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegistrationGenreSearchScreen(
    onBackClick: () -> Unit,
    genreList: List<String>,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
    onGenreSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val paddedModifier = Modifier.padding(horizontal = 20.dp)

    LazyColumn(
        modifier = modifier
            .background(NapzakMarketTheme.colors.white),
    ) {
        stickyHeader {
            BackTopBar(
                nickname = stringResource(search_genre),
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
        }
        itemsIndexed(
            items = genreList,
            key = { _, item -> item }
        ) { index, genre ->
            Text(
                modifier = paddedModifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .noRippleClickable { onGenreSelect(genre) },
                text = genre,
                style = NapzakMarketTheme.typography.bodySemi14,
                color = NapzakMarketTheme.colors.gray800,
            )
            if (index != genreList.lastIndex) {
                HorizontalDivider(
                    modifier = paddedModifier,
                    color = NapzakMarketTheme.colors.gray100,
                    thickness = 10.dp,
                )
            }
        }
    }
}

private const val BLANK = ""

@Preview
@Composable
private fun RegistrationGenreSearchScreenPreview() {
    NapzakMarketTheme {
        RegistrationGenreSearchScreen(
            genreList = listOf("건담", "산리오", "주술회전", "건담", "산리오", "주술회전", "건담", "산리오", "주술회전", "건담", "산리오", "주술회전", "건담", "산리오", "주술회전", "건담", "산리오", "주술회전", "건담", "산리오", "주술회전"),
            onBackClick = {},
            searchValue = "",
            onSearchValueChange = {},
            onSearchButtonClick = {},
            onGenreSelect = {},
        )
    }
}
