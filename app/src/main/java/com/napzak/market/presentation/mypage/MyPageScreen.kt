package com.napzak.market.presentation.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.R
import com.napzak.market.core.designsystem.component.button.CommonButton
import com.napzak.market.core.designsystem.component.topbar.CenteredTextTopBar
import com.napzak.market.presentation.mypage.type.MyPageMenuType
import com.napzak.market.R.string.profile_image_description
import com.napzak.market.R.string.view_my_market
import com.napzak.market.core.common.util.NoRippleInteractionSource

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    onMyMarketNavigate: (Long) -> Unit,
    onHistoryNavigate: () -> Unit,
    onGenreNavigate: () -> Unit,
    onFavoriteNavigate: () -> Unit,
    onRecentNavigate: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NapzakMarketTheme.colors.white),
    ) {
        CenteredTextTopBar(
            title = stringResource(id = R.string.mypage_title),
            textStyle = NapzakMarketTheme.typography.titleBold18,
            backgroundColor = NapzakMarketTheme.colors.white,
            contentColor = NapzakMarketTheme.colors.gray900,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NapzakMarketTheme.colors.gray50),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = NapzakMarketTheme.colors.white),
                border = BorderStroke(1.dp, NapzakMarketTheme.colors.gray200),
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(
                                    color = NapzakMarketTheme.colors.purple10,
                                    shape = CircleShape,
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(uiState.profileImageUrl)
                                    .placeholder(R.drawable.ic_profile_basic_60)
                                    .error(R.drawable.ic_profile_basic_60)
                                    .build(),
                                contentDescription = stringResource(id = profile_image_description),
                                modifier = Modifier.size(60.dp),
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = uiState.nickname,
                            style = NapzakMarketTheme.typography.titleBold20,
                            color = NapzakMarketTheme.colors.gray900,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    CommonButton(
                        text = stringResource(id = view_my_market),
                        onClick = {
                            onMyMarketNavigate(1) /* TODO: 인자값 사용자 storeID로 변경 필요 */
                        },
                        buttonColors = ButtonDefaults.buttonColors(containerColor = NapzakMarketTheme.colors.purple30),
                        shape = RoundedCornerShape(12.dp),
                        textStyle = NapzakMarketTheme.typography.bodyBold16,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            Spacer(modifier = Modifier.height(38.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 19.dp, end = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyPageMenuType.entries.forEach { menuType ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable(
                                indication = null,
                                interactionSource = NoRippleInteractionSource
                            ) {
                                when (menuType) {
                                    MyPageMenuType.HISTORY -> onHistoryNavigate()
                                    MyPageMenuType.GENRE -> onGenreNavigate()
                                    MyPageMenuType.FAVORITE -> onFavoriteNavigate()
                                    MyPageMenuType.RECENT -> onRecentNavigate()
                                }
                            },
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = menuType.iconResourceId),
                            contentDescription = stringResource(id = menuType.titleResourceId),
                            modifier = Modifier.size(54.dp),
                            tint = Color.Unspecified,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(id = menuType.titleResourceId),
                            style = NapzakMarketTheme.typography.bodyMedium14,
                            color = NapzakMarketTheme.colors.gray700,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(38.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    NapzakMarketTheme {
        MyPageScreen(
            onMyMarketNavigate = {
                println("Navigating to 'My Market' screen")
            },
            onHistoryNavigate = {
                println("Navigating to 'History' screen")
            },
            onGenreNavigate = {
                println("Navigating to 'Genre' screen")
            },
            onFavoriteNavigate = {
                println("Navigating to 'Favorite' screen")
            },
            onRecentNavigate = {
                println("Navigating to 'Recent' screen")
            }
        )
    }
}
