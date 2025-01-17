package com.napzak.market.presentation.marketinfo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.R
import com.napzak.market.R.string.profile_image_description
import com.napzak.market.core.common.extension.noRippleClickable
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre
import com.napzak.market.presentation.marketinfo.state.MarketUiInformation

@Composable
fun MarketInfoTopSection(
    marketInfo: MarketUiInformation,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(marketInfo.storeBackgroundPhoto)
            .placeholder(R.drawable.img_market_bg)
            .error(R.drawable.img_market_bg)
            .build(),
        contentDescription = stringResource(id = profile_image_description),
        modifier = Modifier.fillMaxWidth(),
    )

    Box {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 38.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_back_48),
                contentDescription = stringResource(R.string.left_chevron_button),
                tint = NapzakMarketTheme.colors.gray900,
                modifier = Modifier.noRippleClickable(onBackButtonClick),
            )

            Spacer(Modifier.height(56.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NapzakMarketTheme.colors.white)
                    .padding(bottom = 20.dp),
                horizontalAlignment = CenterHorizontally,
            ) {
                Spacer(Modifier.height(30.dp))

                Text(
                    text = marketInfo.storeNickname,
                    style = NapzakMarketTheme.typography.titleBold20,
                    color = NapzakMarketTheme.colors.gray900,
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    marketInfo.genrePreferenceList.forEachIndexed { index, genreItem ->
                        MarketGenreChip(genreItem.genreName)

                        if (index != marketInfo.genrePreferenceList.lastIndex) {
                            Spacer(Modifier.width(6.dp))
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = marketInfo.storeDescription,
                    style = NapzakMarketTheme.typography.capMedium12,
                    color = NapzakMarketTheme.colors.gray700,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 77.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .background(
                        color = NapzakMarketTheme.colors.white,
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(marketInfo.storePhoto)
                        .placeholder(R.drawable.ic_profile_basic_60)
                        .error(R.drawable.ic_profile_basic_60)
                        .build(),
                    contentDescription = stringResource(profile_image_description),
                    modifier = Modifier.size(80.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketInfoTopSectionPreview() {
    MarketInfoTopSection(
        marketInfo = MarketUiInformation(
            storeNickname = "납자기",
            storeDescription = "마이멜로디, 시나모롤 제일 좋아합니다 :) 해당 장르 상품들 판매 및 제시 채팅 언제든 환영합니다!",
            storePhoto = "",
            storeBackgroundPhoto = "",
            genrePreferenceList = listOf(
                Genre(
                    genreId = 1,
                    genreName = "나루토",
                ),
                Genre(
                    genreId = 2,
                    genreName = "원피스",
                ),
                Genre(
                    genreId = 3,
                    genreName = "블리치",
                ),
                Genre(
                    genreId = 4,
                    genreName = "귀멸의 칼날",
                ),
            )
        ),
        onBackButtonClick = { },
    )
}