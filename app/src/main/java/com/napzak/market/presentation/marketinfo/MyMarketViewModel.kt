package com.napzak.market.presentation.marketinfo

import androidx.lifecycle.ViewModel
import com.napzak.market.presentation.marketinfo.state.MarketInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyMarketViewModel @Inject constructor(
    /* TODO: Repository 연결 */
): ViewModel() {
    private val _uiState = MutableStateFlow(MarketInfoUiState())
    val uiState = _uiState.asStateFlow()
}