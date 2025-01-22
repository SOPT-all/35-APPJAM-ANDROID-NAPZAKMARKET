package com.napzak.market.presentation.detailpage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.napzak.market.R
import com.napzak.market.R.string.profile_image_description
import com.napzak.market.core.common.extension.formatToPriceString
import com.napzak.market.core.common.util.NoRippleInteractionSource
import com.napzak.market.core.designsystem.component.button.CommonButton
import com.napzak.market.core.designsystem.component.chip.TextChip
import com.napzak.market.core.designsystem.component.chip.model.CustomChipColors
import com.napzak.market.core.designsystem.component.snackbar.CommonSnackBar
import com.napzak.market.core.designsystem.component.topbar.BackTopBar
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.core.type.ProductConditionType
import com.napzak.market.core.type.TradeType
import com.napzak.market.presentation.detailpage.component.ProductInfoSection
import com.napzak.market.presentation.detailpage.state.DetailPageUiState
import com.napzak.market.presentation.detailpage.state.MarketInfoUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailPageRoute(
    viewModel: DetailPageViewModel = hiltViewModel(),
    onItemChatNavigate: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val uiState by viewModel.uiState.collectAsState()

    DetailPageScreen(
        uiState = uiState,
        onChatNavigate = onItemChatNavigate,
        onBackClick = onNavigateUp,
        modifier = modifier,
    )
}

@Composable
fun DetailPageScreen(
    uiState: DetailPageUiState,
    onChatNavigate: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val parsedTradeType = TradeType.fromName(uiState.tradeType)
    val conditionEnum = ProductConditionType.fromCondition(uiState.productCondition)
    var isSnackBarVisible by remember { mutableStateOf(false) }
    val snackBarMessage = stringResource(id = R.string.detail_snackbar_message)

    val coroutine = rememberCoroutineScope()
    LaunchedEffect(isSnackBarVisible) {
        if (isSnackBarVisible) {
            coroutine.launch {
                delay(SNACK_BAR_DURATION)
                isSnackBarVisible = false
            }
        }
    }

    Scaffold(
        topBar = {
            BackTopBar(
                nickname = "",
                onBackClick = onBackClick,
            )
        },
        snackbarHost = {
            if (isSnackBarVisible) {
                CommonSnackBar(
                    message = snackBarMessage,
                    icon = ImageVector.vectorResource(id = R.drawable.ic_heart_toast_18),
                    backgroundColor = NapzakMarketTheme.colors.black70,
                    textColor = NapzakMarketTheme.colors.white,
                    textStyle = NapzakMarketTheme.typography.bodyMedium14,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 6.dp),
                )
            }
        },
        bottomBar = {
            BottomBar(
                onHeartClick = { isSnackBarVisible = true },
                onChatClick = onChatNavigate,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .background(NapzakMarketTheme.colors.gray200),
                contentAlignment = Alignment.Center,
            ) {
                uiState.productPhotoUrls.firstOrNull()?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = stringResource(id = R.string.detail_image_placeholder),
                        modifier = Modifier.fillMaxSize(),
                    )
                } ?: Text(
                    text = stringResource(id = R.string.detail_image_placeholder),
                    fontSize = 16.sp,
                    color = NapzakMarketTheme.colors.gray500,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            ProductInfoSection(
                tradeType = parsedTradeType,
                timeText = uiState.uploadTime,
                views = uiState.viewCount,
                likeCount = uiState.interestCount,
                title = uiState.genreName,
                subtitle = uiState.productName,
                price = "${uiState.price.toString().formatToPriceString()}원",
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    HorizontalDivider(
                        color = NapzakMarketTheme.colors.gray100,
                        thickness = 1.dp,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = uiState.description,
                    style = NapzakMarketTheme.typography.bodyMedium16,
                    color = NapzakMarketTheme.colors.gray900,
                    textAlign = TextAlign.Start,
                )

                if (uiState.tradeType == TradeType.SELL.name) {

                    Spacer(modifier = Modifier.height(35.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(id = R.string.detail_product_status_label),
                            style = NapzakMarketTheme.typography.bodySemi16,
                            color = NapzakMarketTheme.colors.gray800,
                        )
                        val conditionLabel = conditionEnum.label
                        TextChip(
                            text = conditionLabel,
                            textStyle = NapzakMarketTheme.typography.bodySemi14,
                            chipColors = CustomChipColors(
                                contentColor = NapzakMarketTheme.colors.gray900,
                                containerColor = NapzakMarketTheme.colors.gray100,
                            ),
                            innerPadding = PaddingValues(
                                horizontal = 12.dp,
                                vertical = 6.dp,
                            ),
                            shape = RoundedCornerShape(4.dp),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        HorizontalDivider(
                            color = NapzakMarketTheme.colors.gray100,
                            thickness = 1.dp,
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(id = R.string.detail_delivery_fee_label),
                            style = NapzakMarketTheme.typography.bodySemi16,
                            color = NapzakMarketTheme.colors.gray800,
                        )
                        if (uiState.standardDeliveryFee > 0 || uiState.halfDeliveryFee > 0) {
                            Row {
                                uiState.standardDeliveryFee.takeIf { it > 0 }?.let {
                                    Row {
                                        Text(
                                            text = stringResource(id = R.string.detail_delivery_normal),
                                            style = NapzakMarketTheme.typography.bodySemi14,
                                            color = NapzakMarketTheme.colors.gray700,
                                        )

                                        Spacer(modifier = Modifier.width(6.dp))

                                        Text(
                                            text = stringResource(
                                                id = R.string.detail_delivery_fee_normal,
                                                it
                                            ),
                                            style = NapzakMarketTheme.typography.bodySemi16,
                                            color = NapzakMarketTheme.colors.gray900,
                                        )
                                    }
                                }
                                uiState.halfDeliveryFee.takeIf { it > 0 }?.let {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Row {
                                        Text(
                                            text = stringResource(id = R.string.detail_delivery_discounted),
                                            style = NapzakMarketTheme.typography.bodySemi14,
                                            color = NapzakMarketTheme.colors.gray700,
                                        )

                                        Spacer(modifier = Modifier.width(6.dp))

                                        Text(
                                            text = stringResource(
                                                id = R.string.detail_delivery_fee_discounted,
                                                it
                                            ),
                                            style = NapzakMarketTheme.typography.bodySemi16,
                                            color = NapzakMarketTheme.colors.gray900,
                                        )
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = stringResource(id = R.string.detail_delivery_included),
                                style = NapzakMarketTheme.typography.bodySemi16,
                                color = NapzakMarketTheme.colors.gray900,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                HorizontalDivider(
                    color = NapzakMarketTheme.colors.gray50,
                    thickness = 8.dp,
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.detail_market_info_label),
                    style = NapzakMarketTheme.typography.bodySemi16,
                    color = NapzakMarketTheme.colors.gray800,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {/* TODO: 내 마켓 보기로 이동하는 네비게이션 추가 */ }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = NapzakMarketTheme.colors.purple10,
                                shape = CircleShape,
                            ),
                        contentAlignment = Alignment.Center,
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

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = uiState.marketInfo.nickname,
                            style = NapzakMarketTheme.typography.bodyBold16,
                            color = NapzakMarketTheme.colors.gray900,
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Row {
                            Text(
                                text = stringResource(id = R.string.detail_market_products_label),
                                style = NapzakMarketTheme.typography.capMedium12,
                                color = NapzakMarketTheme.colors.gray700,
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.detail_market_products_count,
                                    uiState.marketInfo.totalProducts,
                                ),
                                style = NapzakMarketTheme.typography.capSemi12,
                                color = NapzakMarketTheme.colors.purple30,
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = stringResource(id = R.string.detail_separator_dot),
                                style = NapzakMarketTheme.typography.capMedium12,
                                color = NapzakMarketTheme.colors.gray700,
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = stringResource(id = R.string.detail_market_transactions_label),
                                style = NapzakMarketTheme.typography.capMedium12,
                                color = NapzakMarketTheme.colors.gray700,
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.detail_market_transactions_count,
                                    uiState.marketInfo.totalTransactions,
                                ),
                                style = NapzakMarketTheme.typography.capSemi12,
                                color = NapzakMarketTheme.colors.purple30,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


@Composable
fun BottomBar(
    onHeartClick: () -> Unit,
    onChatClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .background(Color.White)
            .border(
                width = 1.dp,
                color = NapzakMarketTheme.colors.gray200,
            )
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                )
                .border(
                    width = 1.dp,
                    color = NapzakMarketTheme.colors.gray200,
                    shape = RoundedCornerShape(12.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_heart_24),
                contentDescription = stringResource(id = R.string.detail_like_button_description),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = NoRippleInteractionSource,
                    ) {
                        onHeartClick()
                    },
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        CommonButton(
            text = stringResource(id = R.string.detail_chat_button),
            onClick = onChatClick,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = NapzakMarketTheme.colors.gray900,
            ),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = NapzakMarketTheme.typography.bodyBold16,
            modifier = Modifier
                .weight(1f),
        )
    }
}

private const val SNACK_BAR_DURATION = 3000L

@Preview(showBackground = true)
@Composable
fun DetailPageScreenSellPreview() {
    val mockUiState = DetailPageUiState(
        productName = "딸기 마이멜로디 마스코트 인형",
        price = 35000,
        uploadTime = "3시간 전",
        viewCount = 120,
        interestCount = 45,
        description = "딸기 마멜 인형 판매합니다!",
        productCondition = "미개봉",
        tradeType = TradeType.SELL.name,
        standardDeliveryFee = 3000,
        halfDeliveryFee = 1500,
        marketInfo = MarketInfoUiState(
            nickname = "판매자1",
            totalProducts = 15,
            totalTransactions = 20,
        )
    )

    NapzakMarketTheme {
        DetailPageScreen(
            uiState = mockUiState,
            onChatNavigate = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPageScreenBuyPreview() {
    val mockUiState = DetailPageUiState(
        productName = "앙상블스타즈",
        price = 25000,
        uploadTime = "1시간 전",
        viewCount = 87,
        interestCount = 15,
        description = "사용하던 피규어를 구매하려 합니다.",
        productCondition = "아주 좋은 상태",
        tradeType = TradeType.BUY.name,
        marketInfo = MarketInfoUiState(
            nickname = "구매자1",
            totalProducts = 5,
            totalTransactions = 3,
        )
    )

    NapzakMarketTheme {
        DetailPageScreen(
            uiState = mockUiState,
            onChatNavigate = {},
            onBackClick = {}
        )
    }
}
