package com.napzak.market.presentation.registration.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * Registration genre button
 *
 * 클릭 시 장르 검색 화면으로 이동하는 버튼
 *
 * @param onGenreClick
 * @param modifier
 */

@Composable
fun RegistrationGenreButton(
    genre: String,
    onGenreClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .noRippleClickable(onGenreClick)
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.search_genre),
                style = NapzakMarketTheme.typography.bodySemi16,
                color = NapzakMarketTheme.colors.gray900,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                if (genre.isNotEmpty()) {
                    Text(
                        text = genre,
                        style = NapzakMarketTheme.typography.bodySemi16,
                        color = NapzakMarketTheme.colors.purple30,
                    )
                }
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_more_18),
                    contentDescription = null,
                    tint = NapzakMarketTheme.colors.gray400,
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            color = NapzakMarketTheme.colors.gray200,
            thickness = 1.dp,
        )
    }
}

@Preview
@Composable
private fun RegistrationGenreButtonPreview() {
    NapzakMarketTheme {
        RegistrationGenreButton(
            genre = "건담",
            onGenreClick = { }
        )
    }
}
