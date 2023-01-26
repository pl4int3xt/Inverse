package com.example.kinetic.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinetic.constants.Resource
import com.example.kinetic.domain.use_case.SearchGameUseCase
import com.example.kinetic.presentation.home.HomeScreenState
import com.example.kinetic.presentation.uievent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchGameUseCase: SearchGameUseCase
) : ViewModel() {
    var page by mutableStateOf(1)

    var searchQuery by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    private fun searchGame(){
        searchGameUseCase(searchQuery, page).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = SearchScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = SearchScreenState(message = result.message?: "Unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _state.value = SearchScreenState(games = result.data?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(searchScreenEvents: SearchScreenEvents){
        when(searchScreenEvents){
            is SearchScreenEvents.OnSearchClicked -> {
                searchGame()
            }
            is SearchScreenEvents.OnBackClicked -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is SearchScreenEvents.OnSearchQueryChanged ->{
                searchQuery = searchScreenEvents.search
            }
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}