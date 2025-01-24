package com.napzak.market.presentation.explore.search.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun GenreTextChip(
    onGenreChipClick: () -> Unit,
) {
    TextChip(
        text = stringResource(R.string.search_genre),
        textStyle = NapzakMarketTheme.typography.capSemi12,
        modifier = Modifier.clip(RoundedCornerShape(4.dp)),
        chipColors = CustomChipColors(
            contentColor = NapzakMarketTheme.colors.gray600,
            containerColor = NapzakMarketTheme.colors.gray100,
        ),
        innerPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
        onClick = onGenreChipClick
    )
}

@Preview
@Composable
private fun GenreTextChipPreview() {
    GenreTextChip(
        onGenreChipClick = {},
    )
}