package com.napzak.market.presentation.chat.itemchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.chat.repository.ChatRepository
import com.napzak.market.presentation.chat.itemchat.type.ChatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    fun loadChatInfo(productId: Long) {
        viewModelScope.launch {
            chatRepository.fetchChatInfo(productId)
                .onSuccess { chatInfo ->
                    _uiState.value = ChatUiState(
                        loadState = UiState.Success(chatInfo)
                    )
                }
                .onFailure { throwable ->
                    _uiState.value = ChatUiState(
                        loadState = UiState.Failure(throwable.message.toString())
                    )
                }
        }
    }
}
