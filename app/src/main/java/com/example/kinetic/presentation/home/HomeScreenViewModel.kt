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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 20

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
): ViewModel() {
    val currentGames: MutableState<List<GameModel>> = mutableStateOf(ArrayList())
    var darkTheme by mutableStateOf(false)
    val page = mutableStateOf(1)
    private var gamesScrollPosition = 0

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    init {
        getGames()
    }

    fun getGames(){
        resetSearchState()
        getGamesUseCase(page.value, PAGE_SIZE).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(message = result.message?: "Unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    this.currentGames.value = result.data?: emptyList()
                    _state.value = HomeScreenState(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun onEvent(homeScreenEvents: HomeScreenEvents){
        when(homeScreenEvents){
            is HomeScreenEvents.OnSearchClicked -> {
                sendUiEvent(UiEvent.OnNavigate(Screens.SearchScreen.route))
            }
        }
    }

    fun nextPage(){
        if((gamesScrollPosition + 1) >= (page.value * PAGE_SIZE)){
            _state.value = HomeScreenState(isLoading = true)
            incrementPage()
            if (page.value > 1){
                getGamesUseCase(page.value, PAGE_SIZE).onEach { result ->
                    when(result){
                        is Resource.Error -> {
                            _state.value = HomeScreenState(message = result.message?: "Unexpected error occurred")
                            sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                        }
                        is Resource.Success -> {
                            appendGames(result.data?: emptyList())
                        } else -> Unit
                    }
                }.launchIn(viewModelScope)
            }
            _state.value = HomeScreenState(isLoading = true)
        }
    }
    private fun appendGames(games: List<GameModel>){
        val current = ArrayList(this.currentGames.value)
        current.addAll(games)
        this.currentGames.value = current
    }

    private fun resetSearchState(){
        page.value = 1
        onChangeGamesScrollPosition(0)
        this.currentGames.value = listOf()
    }
    private fun incrementPage(){
        page.value = page.value + 1
    }

    fun onChangeGamesScrollPosition(position: Int){
        gamesScrollPosition = position
    }
    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}