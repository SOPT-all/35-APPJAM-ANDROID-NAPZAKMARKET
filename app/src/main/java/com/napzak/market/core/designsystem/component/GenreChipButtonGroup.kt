package com.napzak.market.core.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.button.RoundedIconButton
import com.napzak.market.core.designsystem.component.chip.RemovableChip
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.onboarding.state.OnboardingUiState

/**
 * 장르 리스트와 리셋 버튼을 포함하는 그룹 컴포넌트
 *
 * @param genreList 선택된 장르 리스트
 * @param onResetClick 리셋 버튼 클릭 시 실행할 콜백
 * @param onGenreClick 장르 칩 클릭 시 실행할 콜백
 * @param modifier 수정자
 * @param contentPaddingValues 내부 패딩값
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenreChipButtonGroup(
    genreList: List<Genre>,
    onResetClick: () -> Unit,
    onGenreClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
) {
    CompositionLocalProvider(
        value = LocalOverscrollConfiguration provides null,
        content = {
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                contentPadding = contentPaddingValues,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                stickyHeader {
                    if(genreList.isNotEmpty()) {
                        RoundedIconButton(
                            icon = ImageVector.vectorResource(id = R.drawable.ic_reset_18),
                            onClick = onResetClick
                        )
                    }
                }

                itemsIndexed(items = genreList, key = { _, genre -> genre }) { index, genre ->
                    RemovableChip(
                        text = genre.genreName,
                        onClick = { onGenreClick(genre) },
                        modifier = Modifier.animateItem(
                            fadeInSpec = null,
                            fadeOutSpec = null
                        )
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun GenreChipButtonGroupPreview() {
    NapzakMarketTheme {
        GenreChipButtonGroup(
            genreList = OnboardingUiState.initialGenreList.data,
            modifier = Modifier.padding(start = 20.dp),
            contentPaddingValues = PaddingValues(end = 20.dp),
            onResetClick = { },
            onGenreClick = { }
        )
    }
}