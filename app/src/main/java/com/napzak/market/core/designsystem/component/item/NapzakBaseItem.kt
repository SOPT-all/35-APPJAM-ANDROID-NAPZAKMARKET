package com.napzak.market.core.designsystem.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.R
import com.napzak.market.R.string.napzak_item_price
import com.napzak.market.core.common.extension.formatToPriceString
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.common.extension.throttledNoRippleClickable
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.component.text.SingleLineText
import com.napzak.market.core.designsystem.theme.Gray500
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme

/**
 * 홈/탐색/마켓보기에서 사용되는 아이템 컴포넌트
 *
 * @param isLiked 좋아요 여부
 * @param isMyItem 내 아이템 여부, false일 경우 좋아요 아이콘을 숨김
 * @param isOfferPossible 가격제시 태그 표시 여부
 * @param onItemClick 아이템 클릭 시 실행할 콜백
 * @param onLikeClick 좋아요 클릭 시 실행할 콜백
 * @param modifier 컴포넌트 수정자, 기본 크기를 설정하기 위해 사용
 * @param typeTag 타입 태그 컴포넌트, 프리뷰를 참고하여 구현하면 됨
 */

@Composable
fun NapzakBaseItem(
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
    typeTag: @Composable (BoxScope.() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .noRippleClickable(onItemClick),
    ) {
        ItemImageGroup(
            imgUrl = imgUrl,
            isLiked = isLiked,
            isMyItem = isMyItem,
            typeTag = typeTag,
            onLikeClick = onLikeClick,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
        )

        SingleLineText(
            text = genre,
            style = NapzakMarketTheme.typography.capBold12,
            color = NapzakMarketTheme.colors.gray900,
            modifier = Modifier.padding(top = 6.dp),
        )

        SingleLineText(
            text = title,
            style = NapzakMarketTheme.typography.bodyMedium14,
            color = NapzakMarketTheme.colors.gray800,
            modifier = Modifier.padding(top = 4.dp),
        )

        PriceGroup(
            price = stringResource(napzak_item_price, price.formatToPriceString()),
            isOfferPossible = isOfferPossible,
            modifier = Modifier.padding(top = 4.dp),
        )

        SingleLineText(
            text = createdTime,
            style = NapzakMarketTheme.typography.capMedium12,
            color = NapzakMarketTheme.colors.gray400,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

@Composable
private fun ItemImageGroup(
    imgUrl: String,
    isLiked: Boolean,
    isMyItem: Boolean,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier,
    typeTag: @Composable (BoxScope.() -> Unit)? = null,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier) {
        if (imgUrl.isNotBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(imgUrl).build(),
                contentDescription = stringResource(R.string.napzak_item_content_description),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.matchParentSize()
            )
        } else {
            // TODO: 잘 동작하는지 확인차 넣어둔 코드. 추후 삭제될 예정
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = Gray500)
            )
        }

        if (typeTag != null) {
            typeTag()
        }

        if (!isMyItem) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (isLiked) R.drawable.ic_heart_select_24 else R.drawable.ic_heart_unselect_24
                ),
                contentDescription = stringResource(R.string.napzak_item_content_description),
                tint = Color.Unspecified,
                modifier = Modifier
                    .throttledNoRippleClickable(
                        coroutineScope = coroutineScope,
                        onClick = onLikeClick
                    )
                    .padding(top = 4.dp, end = 6.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
private fun PriceGroup(
    price: String,
    isOfferPossible: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        if (isOfferPossible) {
            TextChip(
                text = stringResource(R.string.napzak_item_suggest),
                textStyle = NapzakMarketTheme.typography.capSemi12,
                chipColors = CustomChipColors(
                    containerColor = NapzakMarketTheme.colors.purple10,
                    contentColor = NapzakMarketTheme.colors.purple30,
                ),
                shape = RoundedCornerShape(4.dp),
                innerPadding = PaddingValues(horizontal = 5.dp, vertical = 3.dp)
            )
        }

        SingleLineText(
            text = price,
            style = NapzakMarketTheme.typography.bodyBold16,
            color = NapzakMarketTheme.colors.gray900,
        )
    }
}