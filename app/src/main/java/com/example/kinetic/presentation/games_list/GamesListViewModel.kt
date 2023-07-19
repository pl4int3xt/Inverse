package com.example.kinetic.presentation.games_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinetic.constants.Resource
import com.example.kinetic.domain.model.GameModel
import com.example.kinetic.domain.use_case.GetGamesByCategoryUseCase
import com.example.kinetic.presentation.search.PAGE_SIZE
import com.example.kinetic.presentation.search.SearchScreenState
import com.example.kinetic.presentation.uievent.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class GamesListViewModel @Inject constructor(
    private val getGamesByCategoryUseCase: GetGamesByCategoryUseCase
): ViewModel() {
    val currentGames: MutableState<List<GameModel>> = mutableStateOf(ArrayList())
    val genre by mutableStateOf("")

    val page = mutableStateOf(1)
    private var gamesScrollPosition = 0

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    fun searchGame(){
        resetSearchState()
        getGamesByCategoryUseCase(genre, page.value, PAGE_SIZE).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = SearchScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = SearchScreenState(message = result.message?: "Unexpected error occurred")
                    _uiEvent.emit(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                }
                is Resource.Success -> {
                    this.currentGames.value = result.data?: emptyList()
                    _state.value = SearchScreenState(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun nextPage(){
        if((gamesScrollPosition + 1) >= (page.value * PAGE_SIZE)){
            _state.value = SearchScreenState(isNextLoading = true)
            incrementPage()
            if (page.value > 1){
                getGamesByCategoryUseCase(genre,page.value, PAGE_SIZE).onEach { result ->
                    when(result){
                        is Resource.Error -> {
                            _state.value = SearchScreenState(message = result.message?: "Unexpected error occurred")
                            _uiEvent.emit(UiEvent.ShowToast(message = result.message?:"unexpected error occurred"))
                        }
                        is Resource.Success -> {
                            appendGames(result.data?: emptyList())
                        } else -> Unit
                    }
                }.launchIn(viewModelScope)
            }
            _state.value = SearchScreenState(isNextLoading = false)
        }
    }
    private fun appendGames(games: List<GameModel>){
        val current = ArrayList(this.currentGames.value)
        current.addAll(games)
        this.currentGames.value = current
    }

    fun resetSearchState(){
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

    fun onEvent(gamesListEvents: GamesListEvents){
        when(gamesListEvents){
            is GamesListEvents.OnGameClicked -> {
                viewModelScope.launch {

                }
            }
            is GamesListEvents.OnPopBackStack -> {
                viewModelScope.launch {
                    _uiEvent.emit(UiEvent.PopBackStack)
                }
            }
        }
    }
}