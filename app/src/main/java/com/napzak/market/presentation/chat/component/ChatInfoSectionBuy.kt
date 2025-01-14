package com.napzak.market.presentation.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

@Composable
fun ChatInfoSectionBuy(
    title: String,
    description: String,
    priceLabel: String,
    price: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = NapzakMarketTheme.colors.white)
    ) {
        Divider(
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
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "",
                    color = NapzakMarketTheme.colors.gray500,
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        color = NapzakMarketTheme.colors.gray900,
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextChip(
                        text = priceLabel,
                        textStyle = NapzakMarketTheme.typography.capSemi12,
                        chipColors = CustomChipColors(
                            contentColor = NapzakMarketTheme.colors.purple30,
                            containerColor = NapzakMarketTheme.colors.purple10,
                        ),
                        shape = RoundedCornerShape(4.dp),
                        innerPadding = PaddingValues(horizontal = 5.dp, vertical = 3.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = price,
                        color = NapzakMarketTheme.colors.gray900,
                        style = NapzakMarketTheme.typography.bodyBold16,
                    )
                }
            }
        }

        Divider(
            color = NapzakMarketTheme.colors.gray100,
            thickness = 1.dp,
        )
    }
}