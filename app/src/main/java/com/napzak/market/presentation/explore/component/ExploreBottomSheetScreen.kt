package com.napzak.market.presentation.explore.component

import androidx.compose.runtime.Composable
import com.napzak.market.presentation.explore.bottomSheet.SortBottomSheet
import com.napzak.market.presentation.explore.state.ExploreBottomSheetState
import com.napzak.market.presentation.explore.type.ExploreBottomSheetType

@Composable
fun ExploreBottomSheetScreen(
    bottomSheetState: ExploreBottomSheetState,
    sortType: String,
    onDismissRequest: (ExploreBottomSheetType) -> Unit,
    onSortItemClick: (String) -> Unit,
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

        }
    }
}