package com.napzak.market.presentation.chat.itemchat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.common.util.NoRippleInteractionSource
import com.napzak.market.core.designsystem.component.topbar.BackTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.chat.itemchat.component.ChatInfoSection
import com.napzak.market.presentation.chat.itemchat.type.ChatType

@Composable
fun ItemChatRoute(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {},
    ) {
    ItemChatScreen(
        chatType = ChatType.BUY,
        title = stringResource(id = R.string.chat_screen_title_buy),
        description = stringResource(id = R.string.chat_screen_description_buy),
        price = stringResource(id = R.string.chat_screen_price_buy),
        showPriceLabel = true,
        onBackClick = onNavigateUp,
        modifier = modifier,
    )
}

@Composable
fun ItemChatScreen(
    chatType: ChatType,
    title: String,
    description: String,
    price: String,
    showPriceLabel: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.white),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            BackTopBar(
                title = title,
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
                textStyle = NapzakMarketTheme.typography.titleSemi18,
                backgroundColor = NapzakMarketTheme.colors.white,
                contentColor = NapzakMarketTheme.colors.gray900,
            )

            ChatInfoSection(
                title = when (chatType) {
                    ChatType.BUY -> stringResource(id = R.string.chat_buy_title)
                    ChatType.SELL -> stringResource(id = R.string.chat_sell_title)
                },
                description = description,
                price = price,
                showPriceLabel = showPriceLabel,
                titleColor = if (chatType == ChatType.BUY) NapzakMarketTheme.colors.gray900 else NapzakMarketTheme.colors.purple30
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = NapzakMarketTheme.colors.gray50),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.img_chat_background),
                        contentDescription = stringResource(id = R.string.chat_background_image_description),
                    )
                }
            }

            HorizontalDivider(
                color = NapzakMarketTheme.colors.gray100,
                thickness = 1.dp,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        // TODO: 파일 첨부 기능 구현
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = NapzakMarketTheme.colors.gray100,
                            shape = CircleShape,
                        ),
                    interactionSource = NoRippleInteractionSource
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_13),
                        contentDescription = stringResource(id = R.string.chat_description_file_attach),
                        tint = NapzakMarketTheme.colors.gray600,
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.chat_message_placeholder),
                            color = NapzakMarketTheme.colors.gray400,
                            style = NapzakMarketTheme.typography.bodyMedium14,
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(
                            color = NapzakMarketTheme.colors.gray100,
                            shape = RoundedCornerShape(24.dp),
                        ),
                    shape = RoundedCornerShape(24.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = NapzakMarketTheme.colors.gray100,
                        unfocusedContainerColor = NapzakMarketTheme.colors.gray100,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = NapzakMarketTheme.colors.purple30,
                    ),
                    singleLine = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenBuyPreview() {
    ItemChatScreen(
        chatType = ChatType.BUY,
        title = "납작한 외계인",
        description = "양스타 토모에 히요리 이츠누이 함께",
        price = "100,000원대",
        showPriceLabel = true,
        onBackClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ChatScreenSellPreview() {
    ItemChatScreen(
        chatType = ChatType.SELL,
        title = "납작한 외계인",
        description = "딸기 마이멜로디 마스코트 인형",
        price = "35,000원",
        showPriceLabel = false,
        onBackClick = {}
    )
}
