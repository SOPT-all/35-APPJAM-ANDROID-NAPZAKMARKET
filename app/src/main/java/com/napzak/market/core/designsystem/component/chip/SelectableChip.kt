package com.napzak.market.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.chip.type.SelectableChipType
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.domain.genre.model.Genre

/**
 * 선택가능한 chip 컴포넌트
 *
 * @param isSelected chip의 선택 여부를 나타내는 값
 * @param selectableChipType chip의 타입을 나타내는 값
 * @param onClick chip 선택시 실행되는 콜백
 * @param modifier 수정자
 * @param genreList chip 타입이 GENRE일 경우 선택된 장르의 텍스트 리스트
 */

@Composable
fun SelectableChip(
    isSelected: Boolean,
    selectableChipType: SelectableChipType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    genreList: List<Genre> = emptyList(),
) {
    val colors = NapzakMarketTheme.colors
    val backgroundColor = if (isSelected) colors.gray900 else colors.white
    val borderColor = if (isSelected) colors.gray900 else colors.gray300
    val textColor = if (isSelected) colors.white else colors.gray700
    val iconTintColor = if (isSelected) colors.white else colors.gray500

    Row(
        modifier = modifier
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(100.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .noRippleClickable(onClick),
    ) {
        Text(
            text = generateChipText(selectableChipType, genreList),
            style = NapzakMarketTheme.typography.capSemi12,
            color = textColor,
        )
        if (selectableChipType == SelectableChipType.GENRE) {
            Spacer(Modifier.width(2.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_down_chevron_16),
                contentDescription = stringResource(id = R.string.down_chevron_button),
                tint = iconTintColor,
            )
        }
    }
}

/**
 * SelectableChip의 텍스트를 생성하는 함수
 */
@Composable
private fun generateChipText(type: SelectableChipType, genreList: List<Genre>): String =
    when (type) {
        SelectableChipType.GENRE -> {
            when {
                genreList.isEmpty() -> type.label
                genreList.size == 1 -> genreList[0].genreName
                else -> stringResource(
                    id = R.string.selectablechip_genre_counting,
                    genreList[0],
                    genreList.size - 1
                )
            }
        }
        else -> type.label
    }

@Preview
@Composable
private fun Chip_Genre_Preview(modifier: Modifier = Modifier) {
    SelectableChip(
        isSelected = false,
        modifier = modifier,
        selectableChipType = SelectableChipType.GENRE,
        onClick = { },
        genreList = emptyList()
    )
}

@Preview
@Composable
private fun Chip_Genre_Selected_Preview(modifier: Modifier = Modifier) {
    SelectableChip(
        isSelected = true,
        modifier = modifier,
        selectableChipType = SelectableChipType.GENRE,
        onClick = { },
        genreList = listOf(Genre(genreId = 1, genreName = "마이멜로디"))
    )
}

@Preview
@Composable
private fun Chip_Sold_Out_Preview(modifier: Modifier = Modifier) {
    SelectableChip(
        isSelected = false,
        modifier = modifier,
        selectableChipType = SelectableChipType.SOLD_OUT,
        onClick = { },
    )
}

@Preview
@Composable
private fun Chip_Sold_Out_Selected_Preview(modifier: Modifier = Modifier) {
    SelectableChip(
        isSelected = true,
        modifier = modifier,
        selectableChipType = SelectableChipType.SOLD_OUT,
        onClick = { },
    )
}