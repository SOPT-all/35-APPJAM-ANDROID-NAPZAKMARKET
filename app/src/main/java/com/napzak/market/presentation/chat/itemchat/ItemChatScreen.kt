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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.napzak.market.R
import com.napzak.market.core.common.extension.formatToPriceString
import com.napzak.market.core.common.state.UiState
import com.napzak.market.core.common.util.NoRippleInteractionSource
import com.napzak.market.core.designsystem.component.topbar.BackTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.TradeType
import com.napzak.market.domain.chat.model.ChatInfo
import com.napzak.market.presentation.chat.itemchat.component.ChatInfoSection
import com.napzak.market.presentation.chat.itemchat.type.ChatUiState

@Composable
fun ItemChatRoute(
    viewModel: ChatViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {},
    productId: Long,
) {

    LaunchedEffect(productId) {
        viewModel.loadChatInfo(productId)
    }

    val uiState by viewModel.uiState.collectAsState()

    ItemChatScreen(
        uiState = uiState,
        onBackClick = onNavigateUp,
        modifier = modifier,
    )
}

@Composable
fun ItemChatScreen(
    uiState: ChatUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState.loadState) {
        is UiState.Empty -> {}
        is UiState.Loading -> {}
        is UiState.Failure -> {}
        is UiState.Success -> {
            SuccessScreen(
                chatInfo = uiState.loadState.data,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun SuccessScreen(
chatInfo: ChatInfo,
onBackClick: () -> Unit,
modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.white),
    ) {
        BackTopBar(
            title = chatInfo.nickname,
            onBackClick = onBackClick,
            modifier = Modifier.fillMaxWidth(),
            textStyle = NapzakMarketTheme.typography.titleSemi18,
            backgroundColor = NapzakMarketTheme.colors.white,
            contentColor = NapzakMarketTheme.colors.gray900,
        )

        ChatInfoSection(
            tradeType = chatInfo.tradeType,
            title = chatInfo.title,
            firstPhoto = chatInfo.firstPhoto,
            price = "${
                chatInfo.price.toString().formatToPriceString()
            }${stringResource(id = R.string.chat_price_unit)}",
            isPriceNegotiable = chatInfo.isPriceNegotiable,
            titleColor = when (TradeType.fromName(chatInfo.tradeType)) {
                TradeType.BUY -> NapzakMarketTheme.colors.gray900
                TradeType.SELL -> NapzakMarketTheme.colors.purple30
            },
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
                interactionSource = NoRippleInteractionSource,
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
                singleLine = true,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemChatScreenPreview() {
    val mockUiState = ChatUiState(
        UiState.Success(
            ChatInfo(
                nickname = "Seller123",
                firstPhoto = "",
                tradeType = "BUY",
                title = "Product Title",
                price = 150000,
                isPriceNegotiable = true
            )
        )
    )

    NapzakMarketTheme {
        ItemChatScreen(
            uiState = mockUiState,
            onBackClick = {}
        )
    }
}

