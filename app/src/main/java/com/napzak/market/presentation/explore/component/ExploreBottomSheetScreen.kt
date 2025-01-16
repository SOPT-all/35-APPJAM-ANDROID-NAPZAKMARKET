package com.napzak.market.presentation.explore.component

import androidx.compose.runtime.Composable
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.bottomSheet.GenreSearchBottomSheet
import com.napzak.market.presentation.explore.bottomSheet.SortBottomSheet
import com.napzak.market.presentation.explore.state.ExploreBottomSheetState
import com.napzak.market.presentation.explore.type.ExploreBottomSheetType

@Composable
fun ExploreBottomSheetScreen(
    bottomSheetState: ExploreBottomSheetState,
    selectedGenreList: List<Genre>,
    initialGenreList: List<Genre>,
    genreList: List<Genre>,
    sortType: String,
    onDismissRequest: (ExploreBottomSheetType) -> Unit,
    onSortItemClick: (String) -> Unit,
    onTextChange: (String) -> Unit,
    onGenreSelectButtonClick: (List<Genre>) -> Unit,
) {
    with(bottomSheetState) {
        if (isSortBottomSheetVisible) {
            SortBottomSheet(
                selectedSortType = sortType,
                onDismissRequest = { onDismissRequest(ExploreBottomSheetType.SORT) },
                onSortItemClick = onSortItemClick,
            )
        }

        if (isGenreSearchingBottomSheetVisible) {
            GenreSearchBottomSheet(
                initialSelectedGenreList = selectedGenreList,
                initialGenreList = initialGenreList,
                genreList = genreList,
                onDismissRequest = { onDismissRequest(ExploreBottomSheetType.GENRE_SEARCHING) },
                onTextChange = onTextChange,
                onButtonClick = {
                    onGenreSelectButtonClick(it)
                    onDismissRequest(ExploreBottomSheetType.GENRE_SEARCHING)
                },
            )
        }
    }
}