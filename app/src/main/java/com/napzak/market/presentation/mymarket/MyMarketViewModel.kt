package com.napzak.market.presentation.mymarket

import androidx.lifecycle.ViewModel
import com.napzak.market.presentation.mymarket.state.MyMarketUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyMarketViewModel @Inject constructor(
    /* TODO: Repository 연결 */
): ViewModel() {
    private val _uiState = MutableStateFlow(MyMarketUiState())
    val uiState = _uiState.asStateFlow()
}