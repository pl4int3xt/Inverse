package com.example.kinetic.presentation.home

import android.util.Log
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
    var darkTheme by mutableStateOf(false)
    val page = mutableStateOf(1)
    private var gamesScrollPosition = 0

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(HomeScreenState())

    init {
        getGames()
    }

    fun getGames(){
        resetSearchState()
        getGamesUseCase(page.value, PAGE_SIZE).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
                is Resource.Error -> {
                    state = state.copy(message = result.message?: "Unexpected error occurred")
                    sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    state = state.copy(games = result.data?: emptyList())
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
        viewModelScope.launch {
            if((gamesScrollPosition + 1) >= (page.value * PAGE_SIZE)){
                state = state.copy(isNextLoading = true)
                incrementPage()
                if (page.value > 1){
                    getGamesUseCase(page.value, PAGE_SIZE).onEach { result ->
                        when(result){
                            is Resource.Error -> {
                                state = state.copy(message = result.message?: "Unexpected error occurred")
                                sendUiEvent(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                            }
                            is Resource.Success -> {
                                appendGames(result.data?: emptyList())
                            } else -> Unit
                        }
                    }.launchIn(viewModelScope)
                }
                state = state.copy(isNextLoading = true)
            }
        }
    }
    private fun appendGames(games: List<GameModel>){
        Log.e("------------============doaddoajd", "appendGames:${state.copy().games.size}", )
        val current = ArrayList(state.copy().games)
        current.addAll(games)
        state.copy().games = current
    }

    private fun resetSearchState(){
        page.value = 1
        onChangeGamesScrollPosition(0)
        state.games = listOf()
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