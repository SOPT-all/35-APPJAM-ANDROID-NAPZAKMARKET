package com.napzak.market.presentation.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun ChatInfoSectionSell(
    title: String,
    description: String,
    price: String,
    modifier: Modifier = Modifier,
    titleColor: Color = NapzakMarketTheme.colors.purple30,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = NapzakMarketTheme.colors.white),
    ) {
        HorizontalDivider(
            color = NapzakMarketTheme.colors.gray100,
            thickness = 1.dp,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        NapzakMarketTheme.colors.gray300,
                        shape = RoundedCornerShape(4.dp),
                    ),
            )
            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        color = titleColor,
                        style = NapzakMarketTheme.typography.bodyBold14,
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = description,
                        color = NapzakMarketTheme.colors.gray900,
                        style = NapzakMarketTheme.typography.bodyMedium14,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = price,
                    color = NapzakMarketTheme.colors.gray900,
                    style = NapzakMarketTheme.typography.bodyBold16,
                )
            }
        }

        HorizontalDivider(
            color = NapzakMarketTheme.colors.gray100,
            thickness = 1.dp,
        )
    }
}