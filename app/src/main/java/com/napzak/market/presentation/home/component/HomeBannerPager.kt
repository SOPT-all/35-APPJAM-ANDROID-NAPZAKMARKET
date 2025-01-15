package com.napzak.market.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.indicator.PageIndicator
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay

@Composable
fun HomeBannerPager(
    @DrawableRes bannerImages: ImmutableList<Int>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = BANNER_INITIAL_PAGE,
        pageCount = { Int.MAX_VALUE },
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(BANNER_DELAY)
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = tween(BANNER_TWEEN),
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier,
        ) { page ->
            val currentBanner =
                if (bannerImages.isNotEmpty()) bannerImages[page % bannerImages.size]
                else 0

            Image(
                painter = painterResource(id = currentBanner),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )

            /*
            TODO: 서버 통신 시 사용
            AsyncImage(
                model = ImageRequest.Builder(context).data(currentBanner).build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )*/
        }

        PageIndicator(
            imageCount = bannerImages.size,
            pagerState = pagerState,
            modifier = Modifier.padding(top = 10.dp),
        )
    }
}

private const val BANNER_DELAY = 3000L
private const val BANNER_TWEEN = 1000
private const val BANNER_INITIAL_PAGE = 0


@Preview(showBackground = true)
@Composable
private fun HomeBannerPagerPreview() {
    NapzakMarketTheme {
        HomeBannerPager(
            bannerImages = listOf<Int>(
                R.drawable.img_banner_home1,
                R.drawable.img_banner_home2,
            ).toImmutableList(),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(360 / 230f),
        )
    }
}