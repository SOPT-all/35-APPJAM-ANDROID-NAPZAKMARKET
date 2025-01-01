package com.napzak.market.presentation.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.util.UiState
import com.napzak.market.domain.dummy.model.DummyUser
import com.napzak.market.domain.dummy.repository.DummyRepository
import com.napzak.market.presentation.dummy.state.DummyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val dummyRepository: DummyRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DummyUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchDummyUsers() = viewModelScope.launch {
        dummyRepository.fetchDummyUserList(page = 1)
            .onSuccess { userList ->
                if (userList.isNotEmpty()) {
                    updateDummyUiState(UiState.Success(userList))
                } else {
                    updateDummyUiState(UiState.Empty)
                }
            }
            .onFailure {
                updateDummyUiState(UiState.Failure(it.message ?: "Unknown Error"))
            }
    }

    private fun updateDummyUiState(value: UiState<List<DummyUser>>) {
        _uiState.update { currentState ->
            currentState.copy(
                dummyUsersLoadState = value,
            )
        }
    }
}