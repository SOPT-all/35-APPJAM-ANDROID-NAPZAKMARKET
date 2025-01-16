package com.napzak.market.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.presentation.mypage.state.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState

    init {
        loadMyPageData()
    }

    private fun loadMyPageData() {
        viewModelScope.launch {
            _uiState.value = MyPageUiState(
                profileImageUrl = "https://example.com/profile_image.png",
                nickname = "납자기님"
            )
        }
    }
}
