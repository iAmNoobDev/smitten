package com.example.smytten.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smytten.dto.ContentDTO
import com.example.smytten.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(homeRepository: HomeRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getHomeListing().collect { data ->
                _uiState.value = UiState.Data(data = data)
            }
        }
    }

    fun addOrRmFromCart(id: Long) {
        when(val state = _uiState.value) {
            is UiState.Data -> {
                val data = state.data?.map { contentDTO ->
                   val contentList = contentDTO.data.map { productDataDTO ->
                        if (productDataDTO.id == id) {
                            productDataDTO.copy(added = !productDataDTO.added)
                        } else {
                            productDataDTO
                        }
                    }
                    ContentDTO(type = contentDTO.type, data = contentList)
                }
                _uiState.value = UiState.Data(data)
            }
            else -> {  }
        }
    }

    sealed interface UiState {
        data class Loading(val nothing: Nothing? = null) : UiState
        data class Data(val data: List<ContentDTO>?) : UiState
    }

}