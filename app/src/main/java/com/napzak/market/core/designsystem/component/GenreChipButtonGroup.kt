package com.napzak.market.core.designsystem.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.button.RoundedIconButton
import com.napzak.market.core.designsystem.component.chip.RemovableChip
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.domain.genre.model.Genre

/**
 * 장르 리스트와 리셋 버튼을 포함하는 그룹 컴포넌트
 *
 * @param genreList 선택된 장르 리스트
 * @param onResetClick 리셋 버튼 클릭 시 실행할 콜백
 * @param onGenreClick 장르 칩 클릭 시 실행할 콜백
 * @param modifier 수정자
 * @param backgroundColor 장르칩 그룹의 배경색상
 * @param contentPaddingValues 내부 패딩값
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenreChipButtonGroup(
    genreList: List<Genre>,
    onResetClick: () -> Unit,
    onGenreClick: (Genre) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = NapzakMarketTheme.colors.white,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
) {
    CompositionLocalProvider(
        value = LocalOverscrollConfiguration provides null,
        content = {
            AnimatedContent(
                targetState = genreList.isNotEmpty(),
                transitionSpec = {
                    slideInVertically { fullHeight -> -fullHeight } togetherWith
                            slideOutVertically { fullHeight -> -fullHeight }
                },
                label = "GenreChip",
            ) { hasGenre ->
                if (hasGenre) {
                    LazyRow(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(color = backgroundColor),
                        contentPadding = contentPaddingValues,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        stickyHeader {
                            RoundedIconButton(
                                icon = ImageVector.vectorResource(id = R.drawable.ic_reset_18),
                                onClick = onResetClick,
                                modifier = Modifier.background(
                                    color = backgroundColor,
                                    shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp)
                                )
                            )
                        }

                        items(genreList, key = { it.genreId }) { genre ->
                            RemovableChip(
                                text = genre.genreName,
                                onClick = { onGenreClick(genre) },
                                modifier = Modifier
                                    .animateItem(
                                        fadeInSpec = tween(CHIP_ANIMATION_DURATION),
                                        fadeOutSpec = tween(CHIP_ANIMATION_DURATION),
                                    )
                            )
                        }
                    }
                }
            }
        }
    )
}

private const val CHIP_ANIMATION_DURATION = 200

@Preview(showBackground = true)
@Composable
private fun GenreChipButtonGroupPreview() {
    NapzakMarketTheme {
        GenreChipButtonGroup(
            genreList = listOf(
                Genre(
                    genreId = 1,
                    genreName = "나루토",
                    genreImgUrl = "https://dh.aks.ac.kr/Edu/wiki/images/2/2e/%EB%82%98%EB%A3%A8%ED%86%A0_%EC%A0%84%EC%B2%B4.PNG"
                ),
                Genre(
                    genreId = 2,
                    genreName = "원피스",
                    genreImgUrl = "https://i.namu.wiki/i/LpkAOSE_oosO8tQVw4vZKLqaWyk6pu_pTegqW5WVf3JOqRgHJJJHsl-EpYBifUlZ6wxy7a1913LSlvZHcYc717e82bIl7-lU81Pc0HrhGcP8PTIdYuGH9hyeIrLTqLqWUOn9aUfNf9npxclKVSbceA.webp"
                ),
            ),
            modifier = Modifier.padding(start = 20.dp),
            contentPaddingValues = PaddingValues(end = 20.dp),
            onResetClick = { },
            onGenreClick = { }
        )
    }
}