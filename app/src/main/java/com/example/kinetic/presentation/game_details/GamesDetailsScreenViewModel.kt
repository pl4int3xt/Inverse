package com.example.kinetic.presentation.game_details

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinetic.constants.Resource
import com.example.kinetic.domain.use_case.GetGameDetailsUseCase
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.uievent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesDetailsScreenViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var gameId: String? by mutableStateOf("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(GamesDetailsScreenState())
    val state: State<GamesDetailsScreenState> = _state

    init {
        gameId = savedStateHandle.get<String>("gameId")
        getGameDetails()
    }

    fun getGameDetails(){
        getGameDetailsUseCase(gameId?:"").onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = GamesDetailsScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = GamesDetailsScreenState(message = result.message?: "Unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _state.value = GamesDetailsScreenState(gameDetails = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(gamesDetailsScreenEvents: GamesDetailsScreenEvents){
        when(gamesDetailsScreenEvents){
            is GamesDetailsScreenEvents.OnCancelClicked -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is GamesDetailsScreenEvents.OnSearchClicked -> {
                sendUiEvent(UiEvent.OnNavigate(Screens.SearchScreen.route))
            }
        }
    }
    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}