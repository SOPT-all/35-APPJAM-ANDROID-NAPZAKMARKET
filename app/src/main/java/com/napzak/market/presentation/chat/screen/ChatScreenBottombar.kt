package com.napzak.market.presentation.chat.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource

@Composable
fun PreparingScreen() {
    NapzakMarketTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_chat_ready),
                    contentDescription = stringResource(id = R.string.chat_ready_image_description),
                    modifier = Modifier
                        .width(162.dp)
                        .height(128.dp),
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "준비중이에요",
                    style = NapzakMarketTheme.typography.titleSemi20,
                    color = NapzakMarketTheme.colors.gray800,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "조금만 기다려주세요!",
                    style = NapzakMarketTheme.typography.bodyMedium14,
                    color = NapzakMarketTheme.colors.gray600,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreparingScreenPreview() {
    PreparingScreen()
}