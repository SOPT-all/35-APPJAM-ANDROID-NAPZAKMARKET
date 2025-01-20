package com.napzak.market.presentation.explore.explore.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.napzak.market.core.type.BottomSheetType
import com.napzak.market.core.type.SortType
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.core.designsystem.component.bottomSheet.GenreSearchBottomSheet
import com.napzak.market.core.designsystem.component.bottomSheet.SortBottomSheet
import com.napzak.market.presentation.explore.explore.state.ExploreBottomSheetState

@Composable
fun ExploreBottomSheetScreen(
    modifier: Modifier = Modifier,
    bottomSheetState: ExploreBottomSheetState,
    selectedGenreList: List<Genre>,
    initialGenreList: List<Genre>,
    genreList: List<Genre>,
    sortType: SortType,
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
                    initialGenreList = initialGenreList,
                    genreList = genreList,
                    onDismissRequest = { onDismissRequest(BottomSheetType.GENRE_SEARCHING) },
                    onTextChange = onTextChange,
                    onButtonClick = {
                        onGenreSelectButtonClick(it)
                        onDismissRequest(BottomSheetType.GENRE_SEARCHING)
                    },
                )
            }
        }
    }
}
