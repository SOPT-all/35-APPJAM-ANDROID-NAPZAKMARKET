package com.napzak.market.presentation.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.napzak.market.R
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.designsystem.component.GenreChipButtonGroup
import com.napzak.market.core.designsystem.component.item.OnboardingGenreItem
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.onboarding.component.GradientBox
import com.napzak.market.presentation.onboarding.component.OnboardingBottomBar
import com.napzak.market.presentation.onboarding.component.OnboardingTopBar
import com.napzak.market.presentation.onboarding.state.OnboardingUiState

@Composable
fun OnboardingRoute(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchValue by viewModel.searchValue.collectAsStateWithLifecycle()

    OnboardingScreen(
        uiState = uiState,
        searchValue = searchValue,
        onResetClick = viewModel::clearSelectedGenre,
        onGenreClick = viewModel::selectGenre,
        onCompleteButtonClick = navigateToHome,
        onSkipButtonClick = navigateToHome,
        onTextFieldChange = viewModel::changeSearchText,
        onSearchButtonClick = {},
        modifier = modifier,
    )
}

@Composable
private fun OnboardingScreen(
    uiState: OnboardingUiState,
    searchValue: String,
    onSearchButtonClick: () -> Unit,
    onTextFieldChange: (String) -> Unit,
    onGenreClick: (Genre) -> Unit,
    onResetClick: () -> Unit,
    onCompleteButtonClick: () -> Unit,
    onSkipButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler(
        enabled = searchValue.isNotBlank()
    ) {
        onTextFieldChange("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.white),
    ) {
        OnboardingTopBar(
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        SearchBox(
            placeholder = stringResource(R.string.onboarding_text_field_placeholder),
            searchTerm = searchValue,
            onTextChange = onTextFieldChange,
            onSearchButtonClick = onSearchButtonClick,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp),
        )

        GenreChipButtonGroup(
            genreList = uiState.selectedGenreList,
            onGenreClick = onGenreClick,
            onResetClick = onResetClick,
            contentPaddingValues = PaddingValues(horizontal = 20.dp),
            modifier = Modifier
                .padding(top = 16.dp),
        )

        Box(modifier = Modifier.weight(1f)) {
            when (uiState.genreList) {
                is UiState.Loading -> {}
                is UiState.Empty -> {}
                is UiState.Failure -> {}
                is UiState.Success -> {
                    SuccessScreen(
                        genreList = uiState.genreList.data,
                        selectedGenreList = uiState.selectedGenreList,
                        onGenreClick = onGenreClick,
                    )
                }
            }

        }

        OnboardingBottomBar(
            isButtonEnabled = uiState.selectedGenreList.isNotEmpty(),
            onCompleteClick = onCompleteButtonClick,
            onSkipClick = onSkipButtonClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BoxScope.SuccessScreen(
    genreList: List<Genre>,
    selectedGenreList: List<Genre>,
    onGenreClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        value = LocalOverscrollConfiguration provides null,
        content = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    top = 30.dp,
                    bottom = 54.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(19.dp, Alignment.CenterHorizontally),
                modifier = modifier.padding(horizontal = 20.dp),
            ) {
                items(genreList, key = { it.genreId }) { genre ->
                    OnboardingGenreItem(
                        imgUrl = genre.genreImgUrl.orEmpty(),
                        genreName = genre.genreName,
                        isSelected = selectedGenreList.contains(genre),
                        onItemClick = { onGenreClick(genre) },
                    )
                }
            }

            GradientBox(
                modifier = Modifier.align(Alignment.TopCenter),
                brushColors = listOf(
                    NapzakMarketTheme.colors.white,
                    Color.Transparent,
                ),
            )

            GradientBox(
                modifier = Modifier.align(Alignment.BottomCenter),
                brushColors = listOf(
                    Color.Transparent,
                    NapzakMarketTheme.colors.white,
                ),
            )
        }
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun OnboardingScreenPreview() {
    NapzakMarketTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(),
            searchValue = "",
            onGenreClick = {},
            onCompleteButtonClick = {},
            onSkipButtonClick = {},
            onResetClick = {},
            onTextFieldChange = {},
            onSearchButtonClick = {},
        )
    }
}