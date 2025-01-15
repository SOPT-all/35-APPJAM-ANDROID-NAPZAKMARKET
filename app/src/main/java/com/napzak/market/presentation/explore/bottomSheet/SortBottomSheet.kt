package com.napzak.market.presentation.explore.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults.DragHandle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.explore.type.SortType
import com.napzak.market.R
import com.napzak.market.R.string.check_button
import com.napzak.market.core.common.extension.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    selectedSortType: String,
    onDismissRequest: () -> Unit,
    onSortItemClick: (String) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = NapzakMarketTheme.colors.white,
        scrimColor = NapzakMarketTheme.colors.black70,
        dragHandle = {
            DragHandle(
                color = NapzakMarketTheme.colors.gray200,
                width = 36.dp,
                height = 4.dp,
            )
        },
    ) {
        val sortList = listOf<SortType>(
            SortType.RECENT,
            SortType.POPULAR,
            SortType.HIGH_PRICE,
            SortType.LOW_PRICE,
        )

        Column(
            modifier = Modifier
                .background(NapzakMarketTheme.colors.white)
                .padding(horizontal = 20.dp)
        ) {
            sortList.forEach { sortItem ->
                SortItem(
                    sortType = sortItem.label,
                    isSelected = sortItem.name == selectedSortType,
                    isLastItem = sortItem.name == SortType.LOW_PRICE.name,
                    onSortItemClick = { onSortItemClick(sortItem.name) }
                )
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun SortItem(
    sortType: String,
    isSelected: Boolean,
    isLastItem: Boolean,
    onSortItemClick: () -> Unit,
) {
    val borderColor =
        if (isLastItem) NapzakMarketTheme.colors.white else NapzakMarketTheme.colors.gray100

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable(onSortItemClick)
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height - 1),
                    end = Offset(size.width, size.height - 1),
                    strokeWidth = 1.dp.toPx(),
                )
            }
            .padding(vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = sortType,
            style = NapzakMarketTheme.typography.titleSemi18,
            color = if (isSelected) NapzakMarketTheme.colors.purple30 else NapzakMarketTheme.colors.gray900
        )
        if (isSelected) {
            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                contentDescription = stringResource(check_button),
                tint = NapzakMarketTheme.colors.purple30
            )
        }
    }
}