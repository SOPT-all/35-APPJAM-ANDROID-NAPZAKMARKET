package com.napzak.market.core.designsystem.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun NapzakLogoTopBar(
    modifier: Modifier = Modifier,
    logoArrangement: Arrangement.Horizontal = Arrangement.Start,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(NapzakMarketTheme.colors.white)
            .padding(top = 19.dp, bottom = 15.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = logoArrangement,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo_text),
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NapzakLogoTopBarPreview() {
    NapzakMarketTheme {
        NapzakLogoTopBar()
    }
}
