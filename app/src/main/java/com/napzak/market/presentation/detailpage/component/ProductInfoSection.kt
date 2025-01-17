package com.napzak.market.presentation.detailpage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R
import com.napzak.market.presentation.detailpage.type.ProductChipType

/**
 * 상품 정보 섹션을 구성하는 Composable.
 *
 * 상품 유형, 시간, 조회수, 좋아요 수, 제목, 부제목, 가격 정보를 표시합니다.
 * 필요한 경우 가격 제시 여부를 추가로 표시할 수 있습니다.
 *
 * @param chipType 상품 유형 (BUY: 구해요, SELL: 팔아요)
 * @param timeText 등록 시간 텍스트
 * @param views 조회수
 * @param likes 좋아요 수
 * @param title 상품의 주요 제목
 * @param subtitle 상품의 상세 부제목
 * @param price 상품 가격 정보
 * @param modifier 추가적인 Modifier
 * @param isPriceSuggested 가격 제시 여부 (true: 가격 제시 표시, false: 가격 제시 숨김)
 */
@Composable
fun ProductInfoSection(
    chipType: ProductChipType,
    timeText: String,
    views: Int,
    likes: Int,
    title: String,
    subtitle: String,
    price: String,
    modifier: Modifier = Modifier,
    isPriceSuggested: Boolean = false,
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextChip(
                text = chipType.label,
                chipColors = CustomChipColors(
                    contentColor = NapzakMarketTheme.colors.white,
                    containerColor = if (chipType == ProductChipType.BUY) NapzakMarketTheme.colors.gray900 else NapzakMarketTheme.colors.purple30
                ),
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = timeText,
                    style = NapzakMarketTheme.typography.capMedium12,
                    color = NapzakMarketTheme.colors.gray500,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(1.dp)
                        .background(NapzakMarketTheme.colors.gray200),
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_view_16),
                    contentDescription = stringResource(id = R.string.detail_view_icon_description),
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = views.toString(),
                    style = NapzakMarketTheme.typography.capMedium12,
                    color = NapzakMarketTheme.colors.gray500,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart_sm_16),
                    contentDescription = stringResource(id = R.string.detail_like_icon_description),
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = likes.toString(),
                    style = NapzakMarketTheme.typography.capMedium12,
                    color = NapzakMarketTheme.colors.gray500,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = NapzakMarketTheme.typography.bodyBold16,
            color = NapzakMarketTheme.colors.gray900,
        )
        Text(
            text = subtitle,
            style = NapzakMarketTheme.typography.titleMedium18,
            color = NapzakMarketTheme.colors.gray800,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (chipType == ProductChipType.BUY && isPriceSuggested) {
                TextChip(
                    text = stringResource(id = R.string.detail_price_suggest_chip),
                    chipColors = CustomChipColors(
                        contentColor = NapzakMarketTheme.colors.purple30,
                        containerColor = NapzakMarketTheme.colors.purple10,
                    ),
                    shape = RoundedCornerShape(4.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = price,
                style = NapzakMarketTheme.typography.titleBold20,
                color = NapzakMarketTheme.colors.gray900,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductInfoSectionSellPreview() {
    NapzakMarketTheme {
        ProductInfoSection(
            chipType = ProductChipType.SELL,
            timeText = "1시간 전",
            views = 27,
            likes = 4,
            title = "산리오",
            subtitle = "딸기 마이멜로디 마스코트 인형",
            price = "35,000원",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductInfoSectionBuyWithPriceSuggestionPreview() {
    NapzakMarketTheme {
        ProductInfoSection(
            chipType = ProductChipType.BUY,
            timeText = "1시간 전",
            views = 50,
            likes = 12,
            title = "앙상블스타즈",
            subtitle = "앙스타 토모에 히요리 이츠누이 함께사는누이 곰인형",
            price = "100,000원대",
            isPriceSuggested = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductInfoSectionBuyWithoutPriceSuggestionPreview() {
    NapzakMarketTheme {
        ProductInfoSection(
            chipType = ProductChipType.BUY,
            timeText = "1시간 전",
            views = 50,
            likes = 12,
            title = "앙상블스타즈",
            subtitle = "앙스타 토모에 히요리 이츠누이 함께사는누이 곰인형",
            price = "100,000원대",
            isPriceSuggested = false,
        )
    }
}
