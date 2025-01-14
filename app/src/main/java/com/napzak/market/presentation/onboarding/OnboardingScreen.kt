package com.napzak.market.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.GenreChipButtonGroup
import com.napzak.market.core.designsystem.component.item.OnboardingGenreItem
import com.napzak.market.core.designsystem.component.textField.SearchBox
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.onboarding.component.GradientBox
import com.napzak.market.presentation.onboarding.component.OnboardingBottomBar
import com.napzak.market.presentation.onboarding.component.OnboardingTopBar

@Composable
fun OnboardingRoute(
    modifier: Modifier = Modifier,
) {
    OnboardingScreen(
        genreList = emptyList(),
        selectedGenreList = emptyList(),
        onResetClick = {},
        onGenreClick = {},
        onCompleteButtonClick = {},
        onSkipButtonClick = {},
        searchTerm = "",
        onTextFieldChange = {},
        onSearchButtonClick = {},
        modifier = modifier,
    )
}

@Composable
private fun OnboardingScreen(
    genreList: List<String>,
    selectedGenreList: List<String>,
    searchTerm: String,
    onSearchButtonClick: () -> Unit,
    onTextFieldChange: () -> Unit,
    onGenreClick: () -> Unit,
    onResetClick: () -> Unit,
    onCompleteButtonClick: () -> Unit,
    onSkipButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(NapzakMarketTheme.colors.white)) {
        OnboardingTopBar(
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        SearchBox(
            placeholder = stringResource(R.string.onboarding_text_field_placeholder),
            searchTerm = searchTerm,
            onTextChange = { onTextFieldChange() } ,
            onSearchButtonClick = onSearchButtonClick,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp)
        )

        GenreChipButtonGroup(
            genreList = genreList,
            onGenreClick = {onGenreClick()},
            onResetClick = onResetClick,
            contentPaddingValues = PaddingValues(horizontal = 20.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    top = 30.dp,
                    bottom = 54.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(19.dp, Alignment.CenterHorizontally),
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                items(selectedGenreList) { genre ->
                    OnboardingGenreItem(
                        imgUrl = "",
                        genreName = genre,
                        isSelected = false,
                        onItemClick = {},
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
                modifier = Modifier.align(Alignment.TopCenter),
                brushColors = listOf(
                    Color.Transparent,
                    NapzakMarketTheme.colors.white
                ),
            )
        }

        OnboardingBottomBar(
            isButtonEnabled = true,
            onCompleteClick = onCompleteButtonClick,
            onSkipClick = onSkipButtonClick,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun OnboardingScreenPreview() {
    NapzakMarketTheme {
        OnboardingScreen(
            searchTerm = "",
            genreList = listOf(),
            selectedGenreList = listOf("실바니안", "산리오", "슈가슈가룬", "캐릭캐릭체인지"),
            onGenreClick = {},
            onCompleteButtonClick = {},
            onSkipButtonClick = {},
            onResetClick = {},
            onTextFieldChange = {},
            onSearchButtonClick = {},
        )
    }
}