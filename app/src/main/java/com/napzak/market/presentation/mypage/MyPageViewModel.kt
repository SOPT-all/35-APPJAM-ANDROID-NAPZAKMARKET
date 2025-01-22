package com.napzak.market.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.domain.mypage.repository.MyPageRepository
import com.napzak.market.presentation.mypage.state.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val repository: MyPageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MyPageUiState?>(null)
    val uiState: StateFlow<MyPageUiState?> = _uiState

    fun loadMyPage() {
        viewModelScope.launch {
            repository.fetchMyPageData().onSuccess { response ->
                    _uiState.value = MyPageUiState(
                        storeId = response.storeId,
                        profileImageUrl = response.storePhoto,
                        nickname = response.storeNickname,
                    )
                }.onFailure {
                    //TODO: 실패 시..
                }
        }
    }
}
