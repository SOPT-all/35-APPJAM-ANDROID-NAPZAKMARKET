package com.napzak.market.presentation.explore.explore.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun GenreSearchNoticeSection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
    ) {
        Text(
            text = stringResource(R.string.genre_search_select_genre),
            style = NapzakMarketTheme.typography.titleBold18,
            color = NapzakMarketTheme.colors.gray900,
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.onboarding_top_choice),
            style = NapzakMarketTheme.typography.capSemi12,
            color = NapzakMarketTheme.colors.gray500,
        )
    }
}
