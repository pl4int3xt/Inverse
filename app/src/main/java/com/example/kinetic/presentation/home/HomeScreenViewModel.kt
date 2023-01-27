package com.example.kinetic.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinetic.constants.Resource
import com.example.kinetic.domain.model.GameModel
import com.example.kinetic.domain.use_case.GetGamesUseCase
import com.example.kinetic.presentation.screen.Screens
import com.example.kinetic.presentation.uievent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
): ViewModel() {
    val PAGE_SIZE = 30
    var darkTheme by mutableStateOf(false)

    val page = mutableStateOf(1)

    private var gameScrollPosition = 0

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    init {
        getGames()
    }

    fun getGames(){
        getGamesUseCase(page.value).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(message = result.message?: "Unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    _state.value = HomeScreenState(games = result.data?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun nextPage(){
        viewModelScope.launch {
            if ((gameScrollPosition + 1) >= (page.value * PAGE_SIZE)){
                _state.value.nextLoading = true
                incrementPage()
                delay(1000)
                if (page.value > 1){
                    getGamesUseCase(page.value).onEach { result ->
                        when(result){
                            is Resource.Loading -> {
                                _state.value = HomeScreenState(nextLoading = true)
                            }
                            is Resource.Error -> {
                                _state.value = HomeScreenState(message = result.message?: "Unexpected error occurred")
                                sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                            }
                            is Resource.Success -> {
                                _state.value = HomeScreenState(games = result.data?: emptyList())
                            }
                        }
                        appendGames(result.data?: emptyList())
                    }
                }

                _state.value.nextLoading = false
            }
        }
    }
    private fun appendGames(games: List<GameModel>){
        val current = ArrayList(_state.value.games)
        current.addAll(_state.value.games)
        _state.value.games = current
    }
    private fun incrementPage(){
        page.value = page.value + 1
    }

    fun onChangeGameScrollPosition(position: Int){
        gameScrollPosition = position
    }

    fun onEvent(homeScreenEvents: HomeScreenEvents){
        when(homeScreenEvents){
            is HomeScreenEvents.OnSearchClicked -> {
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