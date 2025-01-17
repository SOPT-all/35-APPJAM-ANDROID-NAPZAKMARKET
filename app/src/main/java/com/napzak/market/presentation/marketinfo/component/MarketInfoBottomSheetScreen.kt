package com.napzak.market.presentation.marketinfo.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.napzak.market.core.type.SortType
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.explore.bottomSheet.GenreSearchBottomSheet
import com.napzak.market.presentation.explore.bottomSheet.SortBottomSheet
import com.napzak.market.presentation.explore.type.ExploreBottomSheetType
import com.napzak.market.presentation.marketinfo.state.MarketInfoBottomSheetState

@Composable
fun MarketInfoBottomSheetScreen(
    modifier: Modifier = Modifier,
    bottomSheetState: MarketInfoBottomSheetState,
    selectedGenreList: List<Genre>,
    initialGenreList: List<Genre>,
    genreList: List<Genre>,
    sortType: SortType,
    onDismissRequest: (ExploreBottomSheetType) -> Unit,
    onSortItemClick: (SortType) -> Unit,
    onTextChange: (String) -> Unit,
    onGenreSelectButtonClick: (List<Genre>) -> Unit,
) {
    with(bottomSheetState) {
        if (isSortBottomSheetVisible) {
            Popup(
                alignment = Alignment.BottomCenter,
                onDismissRequest = { onDismissRequest(ExploreBottomSheetType.GENRE_SEARCHING) },
                properties = PopupProperties(
                    focusable = true,
                    excludeFromSystemGesture = true,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
            ) {
                SortBottomSheet(
                    selectedSortType = sortType,
                    onDismissRequest = { onDismissRequest(ExploreBottomSheetType.SORT) },
                    onSortItemClick = onSortItemClick,
                )
            }
        }

        if (isGenreSearchingBottomSheetVisible) {
            Popup(
                alignment = Alignment.BottomCenter,
                onDismissRequest = { onDismissRequest(ExploreBottomSheetType.GENRE_SEARCHING) },
                properties = PopupProperties(
                    focusable = true,
                    excludeFromSystemGesture = true,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
            ) {
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
}