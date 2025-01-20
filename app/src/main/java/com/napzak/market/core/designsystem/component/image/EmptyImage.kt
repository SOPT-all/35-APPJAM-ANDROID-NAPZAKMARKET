package com.napzak.market.core.designsystem.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun EmptyImage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.img_ready),
            contentDescription = null,
            modifier = Modifier
                .width(162.dp)
                .height(128.dp),
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.ready_notice),
            style = NapzakMarketTheme.typography.titleSemi20,
            color = NapzakMarketTheme.colors.gray800,
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.ready_wait_please),
            style = NapzakMarketTheme.typography.bodyMedium14,
            color = NapzakMarketTheme.colors.gray600,
        )
    }
}

@Preview
@Composable
fun EmptyImagePreview(modifier: Modifier = Modifier) {
    EmptyImage(
        modifier = Modifier,
    )
}