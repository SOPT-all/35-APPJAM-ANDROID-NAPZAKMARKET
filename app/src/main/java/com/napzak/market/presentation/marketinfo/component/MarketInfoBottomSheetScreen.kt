package com.napzak.market.presentation.marketinfo.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.type.BottomSheetType
import com.napzak.market.core.type.SortType
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.core.designsystem.component.bottomSheet.GenreSearchBottomSheet
import com.napzak.market.core.designsystem.component.bottomSheet.SortBottomSheet
import com.napzak.market.presentation.marketinfo.state.MarketInfoBottomSheetState

@Composable
fun MarketInfoBottomSheetScreen(
    modifier: Modifier = Modifier,
    bottomSheetState: MarketInfoBottomSheetState,
    selectedGenreList: List<Genre>,
    genreList: UiState<List<Genre>>,
    sortType: SortType,
    debounce: () -> Unit,
    onDismissRequest: (BottomSheetType) -> Unit,
    onSortItemClick: (SortType) -> Unit,
    onTextChange: (String) -> Unit,
    onGenreSelectButtonClick: (List<Genre>) -> Unit,
) {
    with(bottomSheetState) {
        if (isSortBottomSheetVisible) {
            Popup(
                alignment = Alignment.BottomCenter,
                onDismissRequest = { onDismissRequest(BottomSheetType.SORT) },
                properties = PopupProperties(
                    focusable = true,
                    excludeFromSystemGesture = true,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
            ) {
                SortBottomSheet(
                    selectedSortType = sortType,
                    onDismissRequest = { onDismissRequest(BottomSheetType.SORT) },
                    onSortItemClick = onSortItemClick,
                )
            }
        }

        if (isGenreSearchingBottomSheetVisible) {
            Popup(
                alignment = Alignment.BottomCenter,
                onDismissRequest = { onDismissRequest(BottomSheetType.GENRE_SEARCHING) },
                properties = PopupProperties(
                    focusable = true,
                    excludeFromSystemGesture = true,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),
            ) {
                GenreSearchBottomSheet(
                    initialSelectedGenreList = selectedGenreList,
                    genreList = genreList,
                    debounce = debounce,
                    onDismissRequest = { onDismissRequest(BottomSheetType.GENRE_SEARCHING) },
                    onTextChange = onTextChange,
                    onButtonClick = { selectedGenreList ->
                        onGenreSelectButtonClick(selectedGenreList)
                        onDismissRequest(BottomSheetType.GENRE_SEARCHING)
                    },
                )
            }
        }
    }
}