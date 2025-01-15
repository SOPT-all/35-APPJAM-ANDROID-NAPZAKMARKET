package com.napzak.market.presentation.chat.screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.common.util.NoRippleInteractionSource
import com.napzak.market.core.designsystem.component.topbar.BackTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.chat.component.ChatInfoSection
import com.napzak.market.presentation.chat.type.ChatType

/**
 * 채팅 화면 컴포넌트.
 *
 * 구매 또는 판매 채팅 유형에 따라 제목, 설명, 가격 정보를 표시합니다.
 * 필요에 따라 가격 레이블을 추가로 표시할 수 있습니다.
 *
 * @param chatType 채팅의 유형 (구매 또는 판매)
 * @param title 채팅 화면 상단에 표시될 제목 텍스트
 * @param description 채팅 화면 상단에 표시될 설명 텍스트
 * @param price 채팅 화면 상단에 표시될 가격 정보 텍스트
 * @param showPriceLabel 가격 앞에 레이블을 표시할지 여부 (true: 레이블 표시, false: 레이블 숨김)
 */

@Composable
fun ItemChatScreen(
    chatType: ChatType,
    title: String,
    description: String,
    price: String,
    showPriceLabel: Boolean
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(NapzakMarketTheme.colors.white)) {
        Column(modifier = Modifier.fillMaxSize()) {
            BackTopBar(
                title = title,
                onBackClick = {
                    // TODO: 뒤로가기 액션 구현 필요
                },
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
                        imageVector = ImageVector.vectorResource(id = R.drawable.img_chat_background),
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
                            text = "메시지를 입력하세요.",
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
        showPriceLabel = true
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
        showPriceLabel = false
    )
}
