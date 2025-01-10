package com.napzak.market.core.designsystem.component.item

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 홈/탐색/마켓보기에서 사용되는 구해요 컴포넌트
 *
 * @param isLiked 좋아요 여부
 * @param isMyItem 내 아이템 여부, false일 경우 좋아요 아이콘을 숨김
 * @param onItemClick 아이템 클릭 시 실행할 콜백
 * @param onLikeClick 좋아요 클릭 시 실행할 콜백
 * @param modifier 컴포넌트 수정자, 기본 크기를 설정하기 위해 사용
 */

@Composable
fun NapzakBuyItem(
    title: String,
    genre: String,
    price: String,
    imgUrl: String,
    isLiked: Boolean,
    isMyItem: Boolean,
    isOfferPossible: Boolean,
    createdTime: String,
    onItemClick: () -> Unit,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NapzakBaseItem(
        title = title,
        genre = genre,
        price = price,
        imgUrl = imgUrl,
        isLiked = isLiked,
        isMyItem = isMyItem,
        isOfferPossible = isOfferPossible,
        createdTime = createdTime,
        onItemClick = onItemClick,
        onLikeClick = onLikeClick,
        modifier = modifier.width(150.dp),
        typeTag = {
            TextChip(
                text = stringResource(R.string.napzak_item_buy),
                modifier = Modifier.align(Alignment.TopStart),
                chipColors = CustomChipColors(
                    contentColor = NapzakMarketTheme.colors.white,
                    containerColor = NapzakMarketTheme.colors.gray900,
                )
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun NapzakSellItemPreview() {
    NapzakMarketTheme {
        NapzakBuyItem(
            title = "딸기 마이멜로디 마스코트",
            genre = "산리오",
            price = "35,000원",
            imgUrl = "",
            isLiked = false,
            isMyItem = false,
            isOfferPossible = false,
            createdTime = "1시간 전",
            onItemClick = {},
            onLikeClick = {},
            modifier = Modifier.width(150.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyNapzakSellItemPreview() {
    NapzakMarketTheme {
        NapzakBuyItem(
            title = "딸기 마이멜로디 마스코트",
            genre = "산리오",
            price = "35,000원",
            imgUrl = "",
            isLiked = false,
            isMyItem = true,
            isOfferPossible = true,
            createdTime = "1시간 전",
            onItemClick = {},
            onLikeClick = {},
            modifier = Modifier.width(150.dp),
        )
    }
}