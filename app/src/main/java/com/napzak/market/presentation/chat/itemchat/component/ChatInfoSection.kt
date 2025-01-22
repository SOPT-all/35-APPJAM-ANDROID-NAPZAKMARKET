package com.napzak.market.presentation.chat.itemchat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.TradeType

/**
 * 채팅 화면의 정보 섹션 컴포넌트.
 *
 * 거래 유형, 상품 제목, 가격 등의 정보를 표시하며, 상품 이미지와 추가적인
 * 가격 협상 여부를 포함할 수 있습니다. Modifier와 색상을 커스터마이즈할 수 있습니다.
 *
 * @param tradeType 거래 유형을 나타내는 텍스트 ("구해요", "팔아요").
 * @param title 상품 제목을 나타내는 텍스트.
 * @param price 상품 가격을 나타내는 텍스트.
 * @param firstPhoto 상품 이미지를 나타내는 URL 문자열.
 * @param modifier 외부에서 전달받은 Modifier로 레이아웃 속성을 설정.
 * @param isPriceNegotiable 가격 협상이 가능한지 여부 (기본값: false).
 * @param titleColor 제목 텍스트의 색상 (기본값: NapzakMarketTheme.colors.purple30).
 */

@Composable
fun ChatInfoSection(
    tradeType: String,
    title: String,
    price: String,
    firstPhoto: String,
    modifier: Modifier = Modifier,
    isPriceNegotiable: Boolean = false,
    titleColor: Color = NapzakMarketTheme.colors.purple30,
) {
    val displayTradeType = TradeType.fromName(tradeType).label


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
                        color = NapzakMarketTheme.colors.gray300,
                        shape = RoundedCornerShape(4.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(firstPhoto)
                        .placeholder(R.drawable.ic_profile_basic_60)
                        .error(R.drawable.ic_profile_basic_60)
                        .build(),
                    contentDescription = stringResource(id = R.string.profile_image_description),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = displayTradeType,
                        color = titleColor,
                        style = NapzakMarketTheme.typography.bodyBold14,
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = title,
                        color = NapzakMarketTheme.colors.gray900,
                        style = NapzakMarketTheme.typography.bodyMedium14,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isPriceNegotiable) {
                        TextChip(
                            text = stringResource(id = R.string.chat_price_label),
                            textStyle = NapzakMarketTheme.typography.capSemi12,
                            chipColors = CustomChipColors(
                                contentColor = NapzakMarketTheme.colors.purple30,
                                containerColor = NapzakMarketTheme.colors.purple10,
                            ),
                            shape = RoundedCornerShape(4.dp),
                            innerPadding = PaddingValues(horizontal = 5.dp, vertical = 3.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }

                    Text(
                        text = price,
                        color = NapzakMarketTheme.colors.gray900,
                        style = NapzakMarketTheme.typography.bodyBold16,
                    )
                }
            }
        }

        HorizontalDivider(
            color = NapzakMarketTheme.colors.gray100,
            thickness = 1.dp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatInfoSectionPreview() {
    NapzakMarketTheme {
        ChatInfoSection(
            tradeType = "구해요",
            title = "Product Name",
            price = "100,000원",
            isPriceNegotiable = true,
            firstPhoto = "",
            titleColor = NapzakMarketTheme.colors.purple30
        )
    }
}